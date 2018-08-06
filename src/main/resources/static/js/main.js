var messageAPI = Vue.resource("/message{/id}");

Vue.component("message-row", {
    props: ['message'],
    template: "<div><b>{{message.id}}</b> : {{message.text}} </div>"
});

Vue.component("listMessages", {
    props: ['messagesList'],
    template: '<div><message-row v-for="message in messagesList" :message="message" /><div>',
    created: function () {
        messageAPI.get().then(result =>
        result.json().then(data =>
        data.forEach(message => this.messagesList.push(message))))
    }

});

var Vue = new Vue({
    el: '#app',
    template: "<listMessages :messagesList='messages' />",
    data: {
        messages: []
    }
});