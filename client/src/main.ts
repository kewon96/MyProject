import { createApp } from 'vue'

import { routers } from "./route/routers";
import App from './App.vue'

createApp(App).use(routers).mount('#app');
