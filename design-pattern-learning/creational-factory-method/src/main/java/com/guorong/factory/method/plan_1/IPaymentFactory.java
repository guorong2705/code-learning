package com.guorong.factory.method.plan_1;

/**
 * 支付方式创建工厂
 */
interface IPaymentFactory {

    IPayment createPayment();
}

/**
 * 支付宝工厂类
 */
class AlipayPaymentFactory implements IPaymentFactory {

    @Override
    public IPayment createPayment() {
        return new AlipayPayment();
    }
}
/**
 * 微信支付工厂
 */
class WeChatPaymentFactory implements IPaymentFactory {

    @Override
    public IPayment createPayment() {
        return new WeChatPayment();
    }
}