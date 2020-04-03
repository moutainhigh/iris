package com.zhongke.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单实体类
 * @Author liuli
 * @Date 2020/3/31 16:57
 * @Version 1.0
 **/
@Table(name = "zk_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_id")
    private String orderId; // 订单号
    @Column(name = "order_amount")
    private BigDecimal orderAmount; // 订单金额
    @Column(name = "status")
    private Integer status; // 订单状态: 0 未支付 1 已支付 -1 已取消
    @Column(name = "pay_method")
    private String payMethod; // 支付方式
    @Column(name = "pay_aisle")
    private String payAisle; // 支付通道
    @Column(name = "actually_paid")
    private BigDecimal actuallyPaid; // 实付金额
    @Column(name = "discount")
    private BigDecimal discount; // 优惠金额
    @Column(name = "goods_id")
    private Integer goodId; // 商品id
    @Column(name = "device_id")
    private Integer deviceId; // 设备id
    @Column(name = "cashier_id")
    private Integer cashierId; // 收银员id
    @Column(name = "createtime")
    private Date createTime; // 创建时间
    @Column(name = "pay_time")
    private Date payTime; // 支付时间

    @Transient
    private String storeName; // 门店名称
    @Transient
    private String cashierName; // 收银员名称
    @Transient
    private String startTime; // 起始时间
    @Transient
    private String endTime; // 结束时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getActuallyPaid() {
        return actuallyPaid;
    }

    public void setActuallyPaid(BigDecimal actuallyPaid) {
        this.actuallyPaid = actuallyPaid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayAisle() {
        return payAisle;
    }

    public void setPayAisle(String payAisle) {
        this.payAisle = payAisle;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}