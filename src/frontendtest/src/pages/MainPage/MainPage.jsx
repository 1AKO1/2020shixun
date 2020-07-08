import React from 'react'
import { Layout, Menu } from 'antd';
import {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    UserOutlined,
    SyncOutlined,
    ChromeOutlined,
    SlackOutlined,
    DribbbleOutlined,
    AimOutlined
} from '@ant-design/icons';
import './MainPage.css'
import Logo from '../../logo.svg'
import {HashRouter as Router, Route} from 'react-router-dom'
import Main from "../Main/Main";
const { SubMenu } = Menu;
const { Header, Sider, Content, Footer } = Layout;

export default class MainPage extends React.Component {
    state = {
        collapsed: false,
    };

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    };

    render() {
        return (
            <Layout>
                <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
                    <div className="logo">
                            <img src={Logo} alt="logo" style={{width: 75, height: 75, marginRight: 20}}/>
                            {this.state.collapsed ? null : "React"}
                    </div>
                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                        <Menu.Item key="main" icon={<UserOutlined />}>
                            项目主页
                        </Menu.Item>
                        <SubMenu  key="DuanZ" icon={<DribbbleOutlined spin/>} title="段总">
                            <Menu.Item key="10">数据表单</Menu.Item>
                        </SubMenu>
                        <SubMenu  key="LiuZ" icon={<AimOutlined spin/>}title="刘总">
                            <Menu.Item key="20">数据表单</Menu.Item>
                        </SubMenu>
                        <SubMenu  key="LiaoZ" icon={<SlackOutlined spin/>} title="廖总">
                            <Menu.Item key="30">数据表单</Menu.Item>
                        </SubMenu>
                        <SubMenu key="XueZ" icon={<ChromeOutlined spin/>} title="薛总">
                            <Menu.Item key="40">数据表单</Menu.Item>
                        </SubMenu>
                        <SubMenu key="LDD" icon={<SyncOutlined spin />} title="李弟弟">
                            <Menu.Item key="50">数据表单</Menu.Item>
                        </SubMenu>
                    </Menu>

                </Sider>
                <Layout className="site-layout">
                    <Header className="site-layout-background" style={{ padding: 0}}>
                        {React.createElement(this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
                            className: 'trigger',
                            onClick: this.toggle,
                        })}
                    </Header>
                    <Content
                    className="site-layout-background"
                    style={{
                        margin: '24px 16px',
                        padding: 24,
                        minHeight: 280,
                    }}
                    >
                        <Route path="/mainpage/main" exact component={Main} />
                        <Route path="/mainpage/" />
                    </Content>
                    <Content
                        className="site-layout-background"
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            minHeight: 280,
                        }}
                    >
                    </Content>
                    <Content
                        className="site-layout-background"
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            minHeight: 280,
                        }}
                    >
                    </Content>
                    <Footer style={{ textAlign: 'center' }}>@队友说的都队</Footer>
                </Layout>
            </Layout>
        );
    }
}