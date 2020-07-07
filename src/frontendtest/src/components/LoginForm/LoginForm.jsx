import React from "react"
import { Form, Input, Button, Select } from 'antd';
import axios from 'axios'

const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};
const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};

const LoginForm = () => {
    const [form] = Form.useForm();

    const onFinish = values => {
        console.log(values);
    };

    const onReset = () => {
        form.resetFields();
    };

    const OnSubmit = () => { // 这是我自己写的提交函数
        let data = form.getFieldsValue();
        console.log(data);
        axios.post("http://localhost:8080/logic/user/login",JSON.stringify({
            uid : data.uid,
            pwd : data.pwd
        })).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        })
    }

    return (
        <Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
            <Form.Item name="uid" label="用户名" rules={[{ required: true }]}>
                <Input />
            </Form.Item>
            <Form.Item name="pwd" label="密码" rules={[{ required: true }]}>
                <Input />
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit" onClick={OnSubmit}>
                    登录
                </Button>
                <Button htmlType="button" onClick={onReset}>
                    重置
                </Button>
            </Form.Item>
        </Form>
    );
};

export default LoginForm;