import Alert from './components/Alert';


const version = '1.0.0';
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
