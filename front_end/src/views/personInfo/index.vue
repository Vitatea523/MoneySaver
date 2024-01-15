<template>
  <div class="personInfo-page">
    <s-header @click="toHome"></s-header>
    <div class="back-box"></div>
    <div class="page-title">Profile Settings</div>

    <div class="personInfo-box">
      <ul class="navs">
        <li
          :class="{ cur: nowNavs == item }"
          v-for="item in navs"
          :key="item"
          @click="changeItem(item)"
        >
          {{ item }}
        </li>
      </ul>
      <div class="content">
        <!-- 用户信息 -->
        <div class="item" v-show="nowNavs == 'Personal information'">
          <el-form ref="form" label-width="180px">
            <el-form-item label="User name：">
              <span>{{ info.username }}</span>
            </el-form-item>
            <el-form-item label="Email：">
              <span>{{ info.eMail }}</span>
            </el-form-item>
            <el-form-item label="Address：">
              <span>{{ info.address || "-" }}</span>
            </el-form-item>
          </el-form>
        </div>
        <div class="item" v-show="nowNavs == 'Password change'">
          <el-form
            ref="formEditPwd"
            :model="formEditPwd"
            label-width="180px"
            :rules="formEditPwdRules"
          >
            <el-form-item label="Old password" prop="pwd">
              <el-input type="password" v-model="formEditPwd.pwd"></el-input>
            </el-form-item>
            <el-form-item label="New password" prop="newPwd">
              <el-input type="password" v-model="formEditPwd.newPwd"></el-input>
            </el-form-item>
            <el-form-item label="Repeat password" prop="reNewPwd">
              <el-input
                type="password"
                v-model="formEditPwd.reNewPwd"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="passwordSubmit">Save</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="item" v-show="nowNavs == 'E-mail change'">
          <el-form
            ref="formEditEmail"
            :model="formEditEmail"
            label-width="180px"
            :rules="formEditEmailRules"
          >
            <el-form-item label="Old E-mail">
              <span>{{ info.eMail || "-" }}</span>
            </el-form-item>
            <el-form-item label="New E-mail" prop="newMail">
              <el-input v-model="formEditEmail.newMail"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="emailOnSubmit">Save</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="item" v-show="nowNavs == 'Address change'">
          <el-form
            ref="formEditAddress"
            :model="formEditAddress"
            label-width="180px"
            :rules="formEditAddressRules"
          >
            <el-form-item label="Old Address">
              <span>{{ info.address || "-" }}</span>
            </el-form-item>
            <el-form-item label="New Address" prop="newAddress">
              <el-input v-model="formEditAddress.newAddress"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="addressSubmit">Save</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="item" v-show="nowNavs == 'Favorltes'">

          <div
            v-for="item in favorltes"
            :key="item.productId"
            class="favorltes-item"
          >
            <div class="info-box" @click="toUrl(item.productId)">
              <div class="name f-item">{{ item.productName }}</div>
              <div class="price f-item">{{ item.currentPrice }}</div>
              <div class="price f-item">{{ item.orginalPrice }}</div>
              <div class="price f-item">{{ item.brand }}</div>
              <div class="f-tag">
                {{ item.category }}
              </div>
            </div>
            <el-button
              icon="el-icon-delete"
              size="mini "
              type="danger"
              @click="deleteCollect(item.productId)"
            ></el-button>
          </div>
          <div v-if="favorltes.length <= 0">
            <el-empty :image-size="200"></el-empty>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import sHeader from "../../components/header.vue";
export default {
  components: {
    sHeader,
  },
  data() {
    return {
      nowNavs: "Personal information",
      navs: [
        "Personal information",
        "Password change",
        "E-mail change",
        "Address change",
        "Favorltes",
      ],
      info: {},
      formEditPwd: {
        pwd: "",
        newPwd: "",
        reNewPwd: "",
      },
      formEditPwdRules: {
        pwd: [
          {
            required: true,
            message: "Please input Old Passward",
            trigger: "blur",
          },
        ],
        newPwd: [
          {
            required: true,
            message: "Please input New Passward",
            trigger: "blur",
          },
        ],
        reNewPwd: [
          {
            required: true,
            message: "Please input Repeat Passward",
            trigger: "blur",
          },
        ],
      },
      formEditEmail: {
        newMail: "",
      },
      formEditEmailRules: {
        newMail: [
          {
            required: true,
            message: "Please input newMail",
            trigger: "blur",
          },
        ],
      },
      formEditAddress: {
        newAddress: "",
      },
      formEditAddressRules: {
        newAddress: [
          {
            required: true,
            message: "Please input newAddress",
            trigger: "blur",
          },
        ],
      },
      favorltes: [
        {
          productName: "454545ds4f5sd4f5sd4f",
          currentPrice: "454545ds4f5sd4f5sd4f",
          orginalPrice: "454545ds4f5sd4f5sd4f",
          brand: "454545ds4f5sd4f5sd4f",
          category: "454545ds4f5sd4f5sd4f",
        },{
          productName: "454545ds4f5sd4f5sd4f",
          currentPrice: "d",
          orginalPrice: "d",
          brand: "454545ds4f5ssdd4f5sd4f",
          category: "ds",
        },
      ],
    };
  },
  created() {
    this.getInfo();
    this.collectList();
  },
  methods: {
    toHome() {
      this.$router.push("home");
    },
    getInfo() {
      var target = "/user/detail/" + window.sessionStorage.getItem("token");
      this.$axios.get(target).then(
        (res) => {
          const data = res.data;
          this.info = data;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    changeItem(value) {
      this.nowNavs = value;
    },
    passwordSubmit() {
      this.$refs.formEditPwd.validate((valid) => {
        if (valid) {
          if (this.formEditPwd.newPwd !== this.formEditPwd.reNewPwd) {
            this.$message({
              message: "The input passwords must be consistent",
              type: "error",
            });
            return;
          }
          var api =
            "/user/changePass/" +
            window.sessionStorage.getItem("token") +
            "/" +
            this.formEditPwd.pwd +
            "/" +
            this.formEditPwd.newPwd;
          this.$axios.get(api).then(
            (res) => {
              if (res.code == 400) {
                  this.$message({
                    message: res.msg || "Password is wrong",
                    type: "error",
                  });
              } else {
                this.$message({
                message: "succeeded",
                type: "success",
                });
              }
              console.log(res);
            },
            (err) => {
              this.$message({
                message: err.msg || "The old password is not correct",
                type: "error",
              });
              console.log(err);
            }
          );
        } else {
          return false;
        }
      });
    },
    emailOnSubmit() {
      this.$refs.formEditEmail.validate((valid) => {
        if (valid) {
          this.$axios
            .post("/user/update", {
              userId: window.sessionStorage.getItem("token"),
              eMail: this.formEditEmail.newMail,
            })
            .then(
              (res) => {
                this.$message({
                  message: "succeeded",
                  type: "success",
                });
                console.log(res);
                this.$router.go(0)
              },
              (err) => {
                alert("Something goes wrong");
                console.log(err);
              }
            );
        } else {
          return false;
        }
      });
    },
    addressSubmit() {
      this.$refs.formEditAddress.validate((valid) => {
        if (valid) {
          this.$axios
            .post("/user/update", {
              userId: window.sessionStorage.getItem("token"),
              address: this.formEditAddress.newAddress,
              eMail: "",
            })
            .then(
              (res) => {
                this.$message({
                  message: "succeeded",
                  type: "success",
                });
              },
              (err) => {
                console.log(err);
              }
            );
        } else {
          return false;
        }

      });
    },
    collectList() {
      this.$axios
        .post("/user/collect", {
          userId: window.sessionStorage.getItem("token"),
        })
        .then(
          (res) => {
            const data = res.data;
            this.favorltes = data;
          },
          (err) => {
            console.log(err);
          }
        );
    },
    deleteCollect(id) {
      this.$axios
        .delete("/product/cancelCollect", {
          data: {
            userId: window.sessionStorage.getItem("token"),
            productId: id,
          },
        })
        .then(
          (res) => {
            this.$message({
              message: "succeeded",
              type: "success",
            });
            this.collectList();
          },
          (err) => {
            console.log(err);
          }
        );
    },
    toUrl(id) {
      this.$router.push({
        path: "/home",
        query: { type: "productDetail", id: id },
      });
    },
    toLogin() {
      this.$router.push("login");
    },
  },
};
</script>
<style lang="less">
.personInfo-page {
  .el-card__body {
    padding: 25px 25px 0;
  }
}
</style>
<style lang="less" scoped>
.personInfo-page {
  background-color: #f3f3f3;
  min-height: 100vh;
  .page-title {
    font-size: 36px;
    line-height: 50px;
    margin: 0 0 20px;
  }
  .el-form-item {
    text-align: left;
  }
  .navs-col {
    width: 190px;
    flex: 0 0 190px;
  }
  .navs {
    flex: 0 0 220px;
    width: 220px;
    height: 100%;
    border: 1px solid #e0e0e0;
    li {
      text-align: left;
      padding: 16px 20px;
      background-color: #f7f7f7;
      border-bottom: 1px solid #e4393c;
      color: #666;
      cursor: pointer;
      border-bottom: 1px solid #e0e0e0;
      &.cur {
        color: #fff;
        background-color: #e4393c;
        &:hover {
          color: #fff;
        }
      }
      &:hover {
        color: #e6181c;
      }
    }
  }
  .content {
    padding: 30px 50px;
    .item {
      min-height: 300px;
      .add-btn {
        margin: 0 0 20px;
        text-align: left;
      }
      .favorltes-save-btn {
        margin: 20px 0;
        text-align: center;
      }
    }
    .favorltes-item {
      display: flex;
      align-items: center;
      margin-bottom: 5px;
      .info-box {
        display: flex;
        background-color: #f3f3f3;
        flex: 1 1 auto;
        align-items: center;
        margin-right: 25px;
        cursor: pointer;
      }
      .f-item {
        flex: 1 1 100px;
        word-break: break-all;
        padding: 10px 15px;
        box-sizing: border-box;
        border-right: 1px solid #fff;
        height: 100%;
        text-align: left;
        &.name{
          flex: 1 0 250px;
        }
      }
      .f-tag {
        word-break: break-all;
        width: 120px;
        padding: 10px 15px;
        flex: 1 1 auto;
        text-align: center;
      }
    }
  }
  .personInfo-box {
    display: flex;
    background-color: #fff;
    width: 80%;
    max-width: 1200px;
    min-width: 700px;
    margin: 0 auto;
    .personInfo {
      margin-top: -10px;
      margin-left: 145px;
      text-align: left;
      font-size: 12px;
    }
  }
  .back-box {
    text-align: left;
    width: 90%;
    margin: 30px auto 0px;
  }
}
</style>
