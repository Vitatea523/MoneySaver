<template>
  <div class="home-page">
    <div class="header">
      <div class="logo">
        <span>Money Saver</span>
      </div>
      <div class="search-box">
        <div class="b">
          <el-input v-model="search" placeholder="Just enter product name.">
            <template #suffix>
              <el-button type="danger">Search</el-button>
            </template>
          </el-input>
        </div>
        <div class="hot_keywords">
          <p class="title">Hot keywords:</p>
          <span>Dior  NYX  Maybelling</span>
        </div>
      </div>
      <div class="user-box">
        <el-avatar
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
        <span class="name" @click="toPersonInfo">MyAccount</span>
        <span class="name" @click="tologin">LogOut</span>
      </div>
    </div>
    <div class="navs">
      <ul>
        <li
          @click="changeItem(item)"
          :class="{ cur: nowNavs == item }"
          v-for="item in navs"
          :key="item"
        >
          {{ item }}
        </li>
      </ul>
    </div>
    <div class="content">
      <shopList v-if="nowNavs === 'home'"></shopList>
      <shopGoods
        @changePage="changePage"
        :search="search"
        v-if="nowNavs === 'PRODUCT'"
      ></shopGoods>
      <shopCoupon v-if="nowNavs === 'COUPON'"></shopCoupon>
      <shopCompare
        @changePage="changePage"
        v-if="nowNavs === 'COMPARISON'"
      ></shopCompare>
      <shopMarket v-if="nowNavs === 'MARKET'"></shopMarket>
    </div>
  </div>
</template>

<script>
import shopGoods from "./sub/goods/shop-goods.vue";
import shopCoupon from "./sub/shop-coupon.vue";
import shopCompare from "./sub/shop-compare.vue";
import shopMarket from "./sub/shop-market.vue";
export default {
  components: {
    shopGoods,
    shopCoupon,
    shopCompare,
    shopMarket,
  },
  data() {
    return {
      navs: [ "PRODUCT", "COUPON", "COMPARISON", "MARKET"],
      nowNavs: "PRODUCT",
      search: "",
    };
  },
  created() {
    const query = this.$route.query;
    if (query && query.type === "productDetail" && query.productId) {
      this.goodsId = query.productId
      this.showDetail = true
    }
  },
  methods: {
    changeItem(value) {
      this.nowNavs = value;
    },
    changePage(value) {
      console.log(value);
      this.nowNavs = value;
    },
    toPersonInfo() {
      this.$router.push("personInfo");
    },
    tologin() {
      sessionStorage.clear()
      this.$router.push("login");
    },
  },
};
</script>
<style lang="less">
.home-page {
  .el-tabs__content {
    padding: 0;
  }
  .el-tabs {
    border-radius: 5px;
    overflow: hidden;
  }
  .el-tabs__item {
    padding: 0 10px;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
  }
  .el-tabs__header {
    border-bottom: none !important;
  }
  .el-input__suffix {
    right: 0;
  }
}
</style>
<style lang="less" scoped>
.home-page {
  padding: 0 150px;
  .header {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 120px;
    .logo {
      flex: 0 1 400px;
      text-align: left;
      padding: 10px 0px;
      font-weight: bold;
      font-size: 40px;
      font-family: Tahoma, Geneva, sans-serif;
      background-image: -webkit-linear-gradient(left, #ff5f2e 0%, #ff4213 100%);
      -webkit-text-fill-color: transparent;
      -webkit-background-clip: text;
      -webkit-background-size: 200% 100%;
      -webkit-animation: masked-animation 4s linear infinite;
    }
    .search-box {
      flex: 0 1 50px;
      text-align: left;
      font-size: 0;
      margin: 0 auto;
      position: relative;
      left:-40px;
      .t {
        display: inline-block;
      }
      .b {
        width: 500px;
      }
      .hot_keywords {
        position: absolute;
        left: 0;
        bottom: -30px;
        margin: 10px 0;
        .title {
          color: #000;
          display: inline-block;
          font-size: 14px;
          margin-right: 8px;
        }
        span {
          color: #bbb;
          font-size: 14px;
          margin-right: 8px;

        }
      }
    }
    .user-box {
      flex: 0 1 auto;
      display: flex;
      align-items: center;
      cursor: pointer;
      .el-avatar {
        border: 1px solid #f2f2f2;
      }
      .name {
        font-size: 16px;
        padding: 0 10px;
        color: rgb(68, 68, 68);
        font-weight: bold;
      }
    }
  }
  .navs {
    margin: 0 0 15px;
    ul {
      display: flex;
      border: 1px solid #eee;
      li {
        flex: 1 0 auto;
        background: linear-gradient(to bottom, #f7f7f7 0%, #f7f7f7 100%);
        border-bottom: 1px solid #e4393c;
        color: #666;
        line-height: 46px;
        cursor: pointer;
        font-weight: bold;
        &.cur {
          color: #fff;
          background: linear-gradient(to bottom, #e4393c 0%, #e4393c 100%);
          &:hover {
            color: #fff;
          }
        }
        &:hover {
          color: #e6181c;
        }
      }
    }
  }
}
</style>
