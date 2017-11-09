<style lang="less">
    @import '../../styles/css/menu.less';
</style>

<template>
    <div>
        <template v-for="item in menuList">
            <MenuItem v-if="!item.children || item.children.length==0" :name="item.name" :key="item.path">
                <Icon :type="item.icon" :size="iconSize" :key="item.path"></Icon>
                <span class="layout-text" :key="item.path">{{ itemTitle(item) }}</span>
            </MenuItem>

            <Submenu v-else :name="item.name" :key="item.path">
                <template slot="title">
                    <Icon :type="item.icon" :size="iconSize"></Icon>
                    <span class="layout-text">{{ itemTitle(item) }}</span>
                </template>
                <sidebarSubMenu :menuList = "item.children"></sidebarSubMenu>
            </Submenu>
        </template>
    </div>
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

        };
    },
    name: 'sidebarSubMenu',
    props: {
        slotTopClass: String,
        menuList: Array,
        iconSize: Number
    },
    components: {
        sidebarSubMenu
    },
    computed: {

    },
    methods: {
        itemTitle (item) {
            if (typeof item.title === 'object') {
                return this.$t(item.title.i18n);
            } else {
                return item.title;
            }
        }
    },
    watch: {

    },
    updated () {

    },
    mounted () {

    }
};
</script>
