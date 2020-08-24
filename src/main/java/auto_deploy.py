import jenkins
from time import sleep
import sys
import git
import os


jenkins_server_url='http://10.10.10.42:8001'
api_token='116ec10e4880da9484888f2878cffbc4ac'
user_id='zhaoshuguang'

build_job='geek-icem_backend_build-packages'
deploy_job='geek-icem_backend_sandbox_deploy-application'
# jenkins_server_url='http://127.0.0.1:37555'
# api_token='11778e3abb0971d096cea6ce2b55dfb52a'
# user_id='ad'

repo = git.Repo(r'C:/WorkSpace/server/geek-dmp-api')
server = jenkins.Jenkins(jenkins_server_url, username=user_id, password=api_token)

print('path1',os.path.abspath(os.curdir))
print('path2',os.path.abspath('.'))

global project
profile='dev'

def main(argv=None):
    if argv is None:
        argv = sys.argv
    preprocess()
    tag_no = create_push_tag()
    print('project', project, 'tag_no', tag_no)
    build_result = build_app(project, tag_no)
    if build_result == 'SUCCESS':
        deploy_app(project, tag_no)


## process_tag start ------------------------------
def preprocess():
    if repo.is_dirty():
        print('有未提交的更改，请提交后执行脚本:', repo.is_dirty())
        sys.exit(0)

    remote = repo.remote()
    remote.pull()
    remote.push()
    global project
    project=repo.remotes.origin.url.split('/')[1].split('.')[0]

    print('app_name:', project, '\tbranch:', repo.active_branch)

def tagAddr(t):
    if t.startswith('dev_'):
        index = t.split('.')
        p_index = int(index[0].split("_")[2])
        m_index = int(index[1])
        l_index = int(index[2])
        return (p_index * 500 * 500 + m_index * 500 + l_index)
    else:
        return 0

def next_tag(t):
    tag_no = '0.0.0'
    if t.startswith('dev_'):
        index = t.split('.')
        p_index = index[0].split("_")[2]
        m_index = index[1]
        next_l = int(index[2])+1
        if next_l>500:
            m_index = str(int(m_index)+1)
            next_l = 0
        tags = [str(p_index), str(m_index), str(next_l)]
        tag_no = '.'.join(tags)
    else:
        pass
    n_tag='_'.join([profile, project, tag_no])
    print('next_tag: ', n_tag)
    return n_tag

def max_tag():
    max_tag='_'.join(['dev', project, '0.0.0'])
    tag=repo.git.tag()
    for t in tag.split("\n"):
        if tagAddr(t) > tagAddr(max_tag):
            max_tag=t
    return max_tag

def create_push_tag():
    tag_name=next_tag(max_tag())
    new_tag = repo.create_tag(tag_name)
    repo.remotes.origin.push(new_tag)
    return tag_name

## process_tag end ------------------------------

## build start ----------------------
def build_app(project, branch):
    server.build_job(build_job, parameters={'Project':project, 'Branch':branch})
    sleep(10)
    #获取job名为job_name的job的最后次构建号
    build_number = server.get_job_info(build_job)['lastBuild']['number']
    # last_complete_bulid = server.get_job_info(build_job)['lastCompletedBuild']['number']
    building=server.get_build_info(build_job, build_number)['building']

    print('build_number', build_number, '\tbuilding', building)

    waiting_time=0
    while building and waiting_time<=60:
        sleep(30)
        waiting_time+=30
        building=server.get_build_info(build_job, build_number)['building']
        print('waiting_time', waiting_time, 'building', building)

    if not building:
        build_result=server.get_build_info(build_job, build_number)['result']
        print('构建build结果', build_result)
        print('console_output:', server.get_build_console_output(build_job, build_number))
        return build_result
    return 'FAILURE'
## build end ----------------------



## deploy start ----------------------
def deploy_app(project, branch):
    server.build_job(deploy_job, parameters={'Project':project, 'TAGS':branch, 'PERCENT':'100%'})
    sleep(10)
    #获取job名为job_name的job的最后次构建号
    build_number = server.get_job_info(deploy_job)['lastBuild']['number']
    building=server.get_build_info(deploy_job, build_number)['building']

    print('deploy_number', build_number, '\tbuilding', building)

    waiting_time=0
    while building and waiting_time<=60:
        sleep(30)
        waiting_time+=30
        building=server.get_build_info(deploy_job, build_number)['building']
        print('waiting_time', waiting_time, 'building', building)

    if not building:
        build_result=server.get_build_info(deploy_job, build_number)['result']
        print('构建deploy结果', build_result)
        print('console_output:', server.get_build_console_output(deploy_job, build_number))
        return build_result
    return 'FAILURE'
## deploy end ----------------------

if __name__ == "__main__":
    sys.exit(main())