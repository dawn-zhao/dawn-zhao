package com.dawn.zhao.bean;

import com.dawn.zhao.workbook.Column;

import java.io.Serializable;

public class ReportDto implements Serializable {

    //saas订单号
    @Column(value = "订单号", ordered = 1)
    private String orderCode;
    //交易类型描述
    @Column(value = "交易类型", ordered = 2)
    private String tradeTypeDesc;
    //交易时间
    @Column(value = "交易时间", ordered = 3)
    private String tradeTime;
    //订单状态描述
    @Column(value = "订单状态", ordered = 4)
    private String orderStatusDesc;
    //出货状态描述
    @Column(value = "出库状态", ordered = 5)
    private String stockStatusDesc;
    //支付状态描述
    @Column(value = "支付状态", ordered = 6)
    private String payStatusDesc;
    //交易流水号
    @Column(value = "交易流水号", ordered = 7)
    private String tradeCode;
    //财务ID
    @Column(value = "财务ID", ordered = 8)
    private String financeCode;
    //影院专资ID
    @Column(value = "影院专资ID", ordered = 9)
    private String gbCode;
    //取票号1
    @Column(value = "取票号", ordered = 10)
    private String ticketCode;
    //收取短息手机号
    @Column(value = "接收手机", ordered = 12)
    private String receiveMobile;
    //saas影院名称
    @Column(value = "影院名称", ordered = 13)
    private String cinemaName;
    //城市名称
    @Column(value = "城市", ordered = 14)
    private String cityName;
    //渠道名称
    @Column(value = "交易渠道", ordered = 15)
    private String channelName;
    //类型描述
    @Column(value = "商品类型", ordered = 16)
    private String goodsTypeName;
    //商品名称
    @Column(value = "商品名称", ordered = 17)
    private String goodsName;
    //影片版本
    @Column(value = "影片版本", ordered = 18)
    private String version;
    //放映时间
    @Column(value = "放映时间", ordered = 19)
    private String planTime;
    //厅名
    @Column(value = "放映影厅", ordered = 20)
    private String hallName;
    //数量
    @Column(value = "数量", ordered = 21)
    private String count;
    //座位明细
    @Column(value = "座位明细", ordered = 22)
    private String seatNos;
    //商品金额
    @Column(value = "商品金额", ordered = 23)
    private String goodsMoney;
    //实收金额
    @Column(value = "实收金额", ordered = 24)
    private String receiveMoney;
    //退单手续费
    @Column(value = "退单手续费", ordered = 25)
    private String refundFee;
    //补贴金额
    @Column(value = "补贴金额", ordered = 26)
    private String subsidyMoney;
    //活动ID
    @Column(value = "活动ID", ordered = 27)
    private String activeId;
    //补贴方
    @Column(value = "补贴方", ordered = 28)
    private String subsidyParty;
    //影票实收
    @Column(value = "影票实收", ordered = 29)
    private String ticketReceiveMoney;
    //卖品实收
    @Column(value = "卖品实收", ordered = 30)
    private String coponReceiveMoney;
    //充值实收
    @Column(value = "充值实收", ordered = 31)
    private String rechargeReceiveMoney;
    //开卡实收
    @Column(value = "开卡实收", ordered = 32)
    private String openReceiveMoney;
    //票房金额
    @Column(value = "票房金额", ordered = 34)
    private String boxTicketMoney;
    //服务费
    @Column(value = "服务费", ordered = 35)
    private String serviceMoney;
    //影城结算
    @Column(value = "影城结算", ordered = 36)
    private String settlementMoney;
    //支付方式描述
    @Column(value = "支付方式", ordered = 35)
    private String payTypeDesc;
    //卡号
    @Column(value = "卡号", ordered = 37)
    private String cardNo;
    //影业日期
    @Column(value = "影业日期", ordered = 38)
    private String businessTime;
    //价格说明
    @Column(value = "价格说明", ordered = 39)
    private String priceDesc;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTradeTypeDesc() {
        return tradeTypeDesc;
    }

    public void setTradeTypeDesc(String tradeTypeDesc) {
        this.tradeTypeDesc = tradeTypeDesc;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public String getStockStatusDesc() {
        return stockStatusDesc;
    }

    public void setStockStatusDesc(String stockStatusDesc) {
        this.stockStatusDesc = stockStatusDesc;
    }

    public String getPayStatusDesc() {
        return payStatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSeatNos() {
        return seatNos;
    }

    public void setSeatNos(String seatNos) {
        this.seatNos = seatNos;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(String receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getSubsidyMoney() {
        return subsidyMoney;
    }

    public void setSubsidyMoney(String subsidyMoney) {
        this.subsidyMoney = subsidyMoney;
    }

    public String getActiveId() {
        return activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public String getSubsidyParty() {
        return subsidyParty;
    }

    public void setSubsidyParty(String subsidyParty) {
        this.subsidyParty = subsidyParty;
    }

    public String getTicketReceiveMoney() {
        return ticketReceiveMoney;
    }

    public void setTicketReceiveMoney(String ticketReceiveMoney) {
        this.ticketReceiveMoney = ticketReceiveMoney;
    }

    public String getCoponReceiveMoney() {
        return coponReceiveMoney;
    }

    public void setCoponReceiveMoney(String coponReceiveMoney) {
        this.coponReceiveMoney = coponReceiveMoney;
    }

    public String getRechargeReceiveMoney() {
        return rechargeReceiveMoney;
    }

    public void setRechargeReceiveMoney(String rechargeReceiveMoney) {
        this.rechargeReceiveMoney = rechargeReceiveMoney;
    }

    public String getOpenReceiveMoney() {
        return openReceiveMoney;
    }

    public void setOpenReceiveMoney(String openReceiveMoney) {
        this.openReceiveMoney = openReceiveMoney;
    }

    public String getBoxTicketMoney() {
        return boxTicketMoney;
    }

    public void setBoxTicketMoney(String boxTicketMoney) {
        this.boxTicketMoney = boxTicketMoney;
    }

    public String getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(String settlementMoney) {
        this.settlementMoney = settlementMoney;
    }

    public String getPayTypeDesc() {
        return payTypeDesc;
    }

    public void setPayTypeDesc(String payTypeDesc) {
        this.payTypeDesc = payTypeDesc;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }
}