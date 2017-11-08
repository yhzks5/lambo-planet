
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
          <p class="login-tip">使用用户名密码登录</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
  import Cookies from 'js-cookie';
  import util from '../../../libs/util';

  export default {
    data() {
      return {
        form: {
          userName: 'admin',
          password: '123456'
        },
        rules: {
          userName: [
            {required: true, message: '账号不能为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      handleSubmit() {
        var self = this;
        self.$refs.loginForm.validate((valid) => {
          if (valid) {
            self.doLogin(self.form.userName, self.form.password, "false");
          }
        });
      },
      doLogin(username, password, rememberMe) {
        var self = this;
        util.ajax.post('/upms/sso/login', util.params({
          username, password, rememberMe
        })).then(function (resp) {
          var result = resp.data;
          if (result.code == 1) {
            Cookies.set('user', self.form.userName);
            Cookies.set('password', self.form.password);
            Cookies.set('access', 1);
            window.location.href = config.home_page;
          } else {
            self.$Message.error(result.data);
          }
        }).catch(function () {
          self.$Message.error("服务器内部异常,请稍候再试.");
        })
      }
    }
  };
</script>

<style lang="less" scoped>
  .login {
    width: 100%;
    height: 100%;
    background: url("../styles/login_bg.jpg") center;
    background-size: cover;
    position: relative;
    &-con {
      position: absolute;
      right: 160px;
      top: 50%;
      transform: translateY(-60%);
      width: 300px;
      &-header {
        font-size: 16px;
        font-weight: 300;
        text-align: center;
        padding: 30px 0;
      }
      .form-con {
        padding: 10px 0 0;
      }
      .login-tip {
        font-size: 10px;
        text-align: center;
        color: #c3c3c3;
      }
    }
  }
</style>
