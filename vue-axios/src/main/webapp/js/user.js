var vue = new Vue({
    el: "#app",
    data: {
        user: {id:"1",username:"aaa",password:"",age:"22",sex:"男",email:"wjl@126.com"},
        userList: []
    },
    methods: {
        findAll: function () {
            var _this = this;
            axios.get("/first/user/findAll.do").then(function (response) {
                _this.userList = response.data;
                console.log(_this.userList);
            }).catch(function (err) {
                console.log(err);
            });
        },
        findById: function (userid) {
            var _this = this;
            axios.get("/first/user/findById.do", {
                params: {
                    id: userid
                }
            }).then(function (response) {
              _this.user = response.data;
                $('#myModal').modal("show");
            }).catch(function (err) {
            });

        },
        update: function (user) {
            var _this = this;
            axios.post("/first/user/updateUser.do",_this.user).then(function (response) {
                _this.findAll();
            }).catch(function (err) {
            });
        }
    },
    created(){
        this.findAll();
    }
});