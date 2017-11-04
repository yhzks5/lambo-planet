<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.userName" placeholder="请输入用户名">
                                <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                                <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
import Cookies from 'js-cookie';
import util from '@/libs/util';
export default {
    data () {
        return {
            form: {
                userName: 'admin',
                password: '123456'
            },
            rules: {
                userName: [
                    { required: true, message: '账号不能为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        handleSubmit () {
        	var self = this;
            self.$refs.loginForm.validate((valid) => {
                if (valid) {
                    self.doLogin(self.form.userName,self.form.password,"false");
                }
            });
        },
        doLogin(username,password,rememberMe){
        	var self = this;
        	util.ajax.post('/upms/sso/login', util.params({
        		username,password,rememberMe
        	})).then(function(resp){
        		var result = resp.data;
        		if(result.code == 1){
        			Cookies.set('user', self.form.userName);
	                Cookies.set('password', self.form.password);
	                Cookies.set('access', 1);
	                self.$store.commit('setAvator', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3448484253,3685836170&fm=27&gp=0.jpg');
	                self.$router.push({
                    	name: 'home_index'
                	});
        		}else{
        			self.$Message.error(result.data);
        		}
			}).catch(function(){
				self.$Message.error("服务器内部异常,请稍候再试.");
			})
       }
    }
};
</script>

<style>

</style>
