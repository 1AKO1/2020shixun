import React from "react"
import { Form, Input, Button} from 'antd';
import axios from 'axios'
import Qs from 'qs'
import {Link} from "react-router-dom";
import {BrowserRouter} from "react-router-dom";

const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};
const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};

const LoginForm = (props) => {
    const [form] = Form.useForm();

    const onFinish = values => {
        console.log(values);
    };

    const onReset = () => {
        form.resetFields();
    };

    const OnSubmit = () => { // 这是我自己写的提交函数
        let data = form.getFieldsValue();
        data = Qs.stringify({
            "uid": data.uid,
            "pwd": data.pwd
        })
        console.log(JSON.stringify(data));
    //     axios.post("http://localhost:8080/logic/user/login",data, {headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(function (response) {
    //         console.log(response);
    //     }).catch(function (error) {
    //         console.log(error);
    //     })
        //假装我请求成功了
        // BrowserRouter.push("/mainpage");
        // const history = createBrowserHistory();
        // history.push("/mainpage")
        window.location.href = '#/mainpage';
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
                <Link to="/register">注册</Link>
            </Form.Item>
        </Form>
    );
};

export default LoginForm;