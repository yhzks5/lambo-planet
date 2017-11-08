import Alert from './components/Alert';


const version = '2.2.9';
const install = function(Vue, config = {}) {
    if (install.installed) return;

    Vue.component("Alert", Alert);

};

// auto install
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
};

export default {
    install,
    Alert
};
