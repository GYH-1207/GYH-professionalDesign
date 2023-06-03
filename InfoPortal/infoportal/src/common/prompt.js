import { Message } from 'element-ui';
export default {
    //消息提示框
    promptBox: function (title, status) {
        Message({
            showClose: true,
            message: title,
            type: status
        });
    }
};