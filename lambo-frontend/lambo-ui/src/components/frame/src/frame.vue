<style lang="less">
    @import "../styles/css/frame.less";
</style>
<template>
    <div class="main" :class="{'main-hide-text': hideMenuText}">
        <div class="sidebar-menu-con" :style="{width: hideMenuText?'60px':'200px', overflow: hideMenuText ? 'visible' : 'auto', background: menuTheme === 'dark'?'#495060':'white'}">
            <div class="logo-con">
                <img v-show="!hideMenuText"  src="../styles/images/logo.jpg" key="max-logo" />
                <img v-show="hideMenuText" src="../styles/images/logo-min.jpg" key="min-logo" />
            </div>
            <sidebar-menu v-if="!hideMenuText" :menuList="menuList" :iconSize="14"/>
            <sidebar-menu-shrink :icon-color="menuIconColor" v-else :menuList="menuList"/>
        </div>
        <div class="main-header-con" :style="{paddingLeft: hideMenuText?'60px':'200px'}">
            <div class="main-header">
                <div class="navicon-con">
                    <Button :style="{transform: 'rotateZ(' + (this.hideMenuText ? '-90' : '0') + 'deg)'}" type="text" @click="toggleClick">
                        <Icon type="navicon" size="32"></Icon>
                    </Button>
                </div>
                <div class="header-middle-con">
                    <div class="main-breadcrumb">
                        <breadcrumb-nav :currentPath="currentPath"></breadcrumb-nav>
                    </div>
                </div>
                <div class="header-avator-con">
                    <div @click="goUiDoc" class="tool-btn-con">
                        <Tooltip content="ui文档" placement="bottom">
                            <Icon type="ios-navigate-outline" :size="22"></Icon>
                        </Tooltip>
                    </div>
                    <div @click="goDubbo" class="tool-btn-con">
                        <Tooltip content="dubbo监控台" placement="bottom">
                            <Icon type="android-stopwatch" :size="22"></Icon>
                        </Tooltip>
                    </div>
                    <div @click="goSwagger" class="tool-btn-con">
                        <Tooltip content="后台接口文档" placement="bottom">
                            <Icon type="ios-paper-outline" :size="22"></Icon>
                        </Tooltip>
                    </div>
                    <div @click="handleFullScreen" v-if="showFullScreenBtn" class="tool-btn-con">
                        <Tooltip :content="isFullScreen ? '退出全屏' : '全屏'" placement="bottom">
                            <Icon :type="isFullScreen ? 'arrow-shrink' : 'arrow-expand'" :size="23"></Icon>
                        </Tooltip>
                    </div>
                    <div @click="lockScreen" class="tool-btn-con">
                        <Tooltip content="锁屏" placement="bottom">
                            <Icon type="locked" :size="20"></Icon>
                        </Tooltip>
                    </div>
                    <div @click="showMessage" class="tool-btn-con">
                        <Tooltip :content="messageCount > 0 ? '有' + messageCount + '条未读消息' : '无未读消息'" placement="bottom">
                            <Badge :count="messageCount" dot>
                                <Icon type="ios-bell" :size="22"></Icon>
                            </Badge>
                        </Tooltip>
                    </div>
                    <div class="switch-theme-con">
                        <Row class="switch-theme" type="flex" justify="center" align="middle">
                            <theme-dropdown-menu></theme-dropdown-menu>
                        </Row>
                    </div>
                    <div class="user-dropdown-menu-con">
                        <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
                            <Dropdown transfer trigger="click" @on-click="handleClickUserDropdown">
                                <a href="javascript:void(0)">
                                    <span class="main-user-name">{{ userName }}</span>
                                    <Icon type="arrow-down-b"></Icon>
                                </a>
                                <DropdownMenu slot="list">
                                    <DropdownItem name="ownSpace">个人中心</DropdownItem>
                                    <DropdownItem name="loginout" divided>退出登录</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                            <Avatar :src="avatorPath ? avatorPath : defaultImg" style="background: #619fe7;margin-left: 10px;"></Avatar>
                        </Row>
                    </div>
                </div>
            </div>
            <div class="tags-con">
                <tags-page-opened :pageTagsList="pageTagsList"></tags-page-opened>
            </div>
        </div>
        <div class="single-page-con" :style="{left: hideMenuText?'60px':'200px'}">
            <div class="single-page">
                <slot></slot>
            </div>
        </div>
    </div>
</template>
<script>
    import sidebarMenu from './main_components/sidebarMenu.vue';
    import tagsPageOpened from './main_components/tagsPageOpened.vue';
    import breadcrumbNav from './main_components/breadcrumbNav.vue';
    import themeDropdownMenu from './main_components/themeDropdownMenu.vue';
    import sidebarMenuShrink from './main_components/sidebarMenuShrink.vue';
    import Cookies from 'js-cookie';
    import avatarImg from '../styles/images/avatar.jpg';

    export default {
        components: {
            sidebarMenu,
            tagsPageOpened,
            breadcrumbNav,
            themeDropdownMenu,
            sidebarMenuShrink
        },
        props:{
            menuList : Array,
            menuTheme: String,
            avatorPath : String,
            pageTagsList: Array,
            currentPath:Array,
        },
        data () {
            return {
                spanLeft: 4,
                spanRight: 20,
                currentPageName: '',
                hideMenuText: false,
                userName: '',
                showFullScreenBtn: window.navigator.userAgent.indexOf('MSIE') < 0,
                messageCount: 0,
                lockScreenSize: 0,
                defaultImg:avatarImg,
                isFullScreen:false,
            };
        },
        computed: {

        },
        methods: {
            init () {

            },
            toggleClick () {
                this.hideMenuText = !this.hideMenuText;
            },
            handleClickUserDropdown (name) {

            },
            lockScreen () {
                let lockScreenBack = document.getElementById('lock_screen_back');
                lockScreenBack.style.transition = 'all 3s';
                lockScreenBack.style.zIndex = 10000;
                lockScreenBack.style.boxShadow = '0 0 0 ' + this.lockScreenSize + 'px #667aa6 inset';
                this.showUnlock = true;
                this.$store.commit('lock');
                Cookies.set('last_page_name', this.$route.name); // 本地存储锁屏之前打开的页面以便解锁后打开
                setTimeout(() => {
                    lockScreenBack.style.transition = 'all 0s';
                    this.$router.push({
                        name: 'locking'
                    });
                }, 800);
            },
            checkTag (name) {

            },
            goSwagger() {
                window.open("http://127.0.0.1:1111/swagger-ui.html");
            },
            goUiDoc(){
                window.open("https://www.iviewui.com/");
            },
            goDubbo(){
                window.open("http://127.0.0.1:8091");
            }
        },
        watch: {

        },
        mounted () {
            // 锁屏相关
            let lockScreenBack = document.getElementById('lock_screen_back');
            let x = document.body.clientWidth;
            let y = document.body.clientHeight;
            let r = Math.sqrt(x * x + y * y);
            let size = parseInt(r);
            this.lockScreenSize = size;
            window.addEventListener('resize', () => {
                let x = document.body.clientWidth;
                let y = document.body.clientHeight;
                let r = Math.sqrt(x * x + y * y);
                let size = parseInt(r);
                this.lockScreenSize = size;
                lockScreenBack.style.transition = 'all 0s';
                lockScreenBack.style.width = lockScreenBack.style.height = size + 'px';
            });
            lockScreenBack.style.width = lockScreenBack.style.height = size + 'px';
            // 问候信息相关
            if (!Cookies.get('hasGreet')) {
                let now = new Date();
                let hour = now.getHours();
                let greetingWord = {
                    title: '',
                    words: ''
                };
                let userName = Cookies.get('user');
                if (hour > 5 && hour < 6) {
                    greetingWord = {title: '凌晨好~' + userName, words: '早起的鸟儿有虫吃~'};
                } else if (hour >= 6 && hour < 9) {
                    greetingWord = {title: '早上好~' + userName, words: '来一杯咖啡开启美好的一天~'};
                } else if (hour >= 9 && hour < 12) {
                    greetingWord = {title: '上午好~' + userName, words: '工作要加油哦~'};
                } else if (hour >= 12 && hour < 14) {
                    greetingWord = {title: '中午好~' + userName, words: '午饭要吃饱~'};
                } else if (hour >= 14 && hour < 17) {
                    greetingWord = {title: '下午好~' + userName, words: '下午也要活力满满哦~'};
                } else if (hour >= 17 && hour < 19) {
                    greetingWord = {title: '傍晚好~' + userName, words: '下班没事问候下爸妈吧~'};
                } else if (hour >= 19 && hour < 21) {
                    greetingWord = {title: '晚上好~' + userName, words: '工作之余品一品书香吧~'};
                } else {
                    greetingWord = {title: '深夜好~' + userName, words: '夜深了，注意休息哦~'};
                }
                this.$Notice.config({
                    top: 130
                });
                this.$Notice.info({
                    title: greetingWord.title,
                    desc: greetingWord.words,
                    duration: 4,
                    name: 'greeting'
                });
                Cookies.set('hasGreet', 1);
            }
        },
        created () {
            // 查找当前用户之前登录时设置的主题
            let name = Cookies.get('user');
            if (localStorage.theme) {
                let hasThisUser = JSON.parse(localStorage.theme).some(item => {
                    if (item.userName === name) {
                        this.$store.commit('changeMenuTheme', item.menuTheme);
                        this.$store.commit('changeMainTheme', item.mainTheme);
                        return true;
                    } else {
                        return false;
                    }
                });
                if (!hasThisUser) {
                    this.$store.commit('changeMenuTheme', 'dark');
                    this.$store.commit('changeMainTheme', 'b');
                }
            } else {
                this.$store.commit('changeMenuTheme', 'dark');
                this.$store.commit('changeMainTheme', 'b');
            }
            // 根据用户设置主题
            if (this.$store.state.theme !== 'b') {
                let stylesheetPath = './dist/' + this.$store.state.theme + '.css';
                let themeLink = document.querySelector('link[name="theme"]');
                themeLink.setAttribute('href', stylesheetPath);
            }
            // 显示打开的页面的列表
            this.$store.commit('setOpenedList');
        }
    };
</script>