import Login from './components/login';
import Frame from './components/frame';


const version = '1.0.0';
const install = function(Vue, config = {}) {
    if (install.installed) return;

    Vue.component("LamboLogin", Login);
    Vue.component("LamboFrame", Frame);

};

// auto install
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
};

export default {
    install,
    Login,
    Frame
};
