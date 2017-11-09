<style lang="less">
    @import '../../styles/css/menu.less';
</style>

<template>
    <Menu ref="sideMenu" :active-name="$route.name" :open-names="openedSubmenuArr" :theme="$store.state.menuTheme" width="auto" @on-select="changeMenu">
        <sidebarSubMenu :menuList = "menuList"></sidebarSubMenu>
    </Menu>
</template>

<script>
import util from '@/libs/util';
import Vue from 'vue';
import VueI18n from 'vue-i18n';
import sidebarSubMenu from './sidebarSubMenu.vue';
Vue.use(VueI18n);
export default {
    data () {
        return {
            openedSubmenuArr: this.$store.state.openedSubmenuArr
        };
    },
    name: 'sidebarMenu',
    props: {
        slotTopClass: String,
        menuList: Array,
        iconSize: Number
    },
    components: {
        sidebarSubMenu
    },
    computed: {
        tagsList () {
            return this.$store.state.tagsList;
        }
    },
    methods: {
        changeMenu (active) {
            console.log("active=="+active);
            if (active !== 'accesstest_index') {
                util.openNewPage(this, active);
                this.$router.push({
                    name: active
                });
            }
        },
        itemTitle (item) {
            if (typeof item.title === 'object') {
                return this.$t(item.title.i18n);
            } else {
                return item.title;
            }
        }
    },
    watch: {
        '$route' (to) {
            localStorage.currentPageName = to.name;
        },
        currentPageName () {
            this.openedSubmenuArr = this.$store.state.openedSubmenuArr;
            this.$nextTick(() => {
                this.$refs.sideMenu.updateOpened();
            });
        }
    },
    updated () {
        this.$nextTick(() => {
            this.$refs.sideMenu.updateOpened();
        });
    }

};
</script>
