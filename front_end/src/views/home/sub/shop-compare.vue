<template>
  <div class="shop-compare-page">
    <div class="breadcrumb" v-if="goods1.productName">Comparison: {{goods1.productName}} with {{goods2.productName}}</div>
    <div class="shop-vs" v-if="goods1.productName">
      <div class="shop-item">
        <div class="shop-title">
          <p class="name">{{goods1.productName}}</p>
          <p class="desc">
            {{goods1.brand}}
          </p>
        </div>
        <div class="shop-content">
          <div class="l">
            <img :src="goods1.picture" alt="" />
          </div>
          <div class="r">
            <ul>
              <li>
                <p>Was</p>
                <p>{{goods1.orginalPrice}}</p>
              </li>
              <li>
                <p>Best price</p>
                <p>{{goods1.currentPrice}}</p>
              </li>
              <li>
                <p>Brand</p>
                <p>{{goods1.brand}}</p>
              </li>
              <li>
                <p>Category</p>
                <p>{{goods1.category}}</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="vs">vs</div>
      <div class="shop-item">
        <div class="shop-title">
          <p class="name">{{goods2.productName}}</p>
          <p class="desc">
            {{goods2.brand}}
          </p>
        </div>
        <div class="shop-content">
          <div class="l">
            <img :src="goods2.picture" alt="" />
          </div>
          <div class="r">
            <ul>
              <li>
                <p>Was</p>
                <p>{{goods2.orginalPrice}}</p>
              </li>
              <li>
                <p>Best price</p>
                <p>{{goods2.currentPrice}}</p>
              </li>
              <li>
                <p>Brand</p>
                <p>{{goods2.brand}}</p>
              </li>
              <li>
                <p>Category</p>
                <p>{{goods2.category}}</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="shop-vs no-data" v-if="!goods1.productName">
      <div class="shop-item" @click="back">
        <div class="add-icon">+</div>
      </div>
      <div class="vs">vs</div>
      <div class="shop-item" @click="back">
        <div class="add-icon">+</div>
      </div>
    </div>
    <div class="btn-box" v-show="goods1.productName">
      <el-button @click="back" type="primary">Back</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      goods1: {},
      goods2: {},
    };
  },
  created() {
    let goods1 = localStorage.getItem("goods1") || '{}';
    let goods2 = localStorage.getItem("goods2") || '{}';
    this.goods1 = goods1 && JSON.parse(goods1);
    this.goods2 = goods2 && JSON.parse(goods2);
  },
  methods:{
    back(){
      this.$emit('changePage','PRODUCT')
    }
  },
  unmounted(){
    localStorage.removeItem("goods1")
    localStorage.removeItem("goods2")
  }
};
</script>

<style lang="less" scoped>
.shop-compare-page {
  margin: 20px 40px;
  .breadcrumb {
    background-color: #f3f3f3;
    padding: 10px 20px;
    text-align: left;
    font-size: 14px;
  }
  .shop-vs {
    margin-top: 10px;
    display: flex;
    align-items: center;
    &.no-data{
      .shop-item{
        display: flex;
        align-items: center;
        justify-content: center;
        height: 300px;
        background-color: #f2f2f2;
        cursor: pointer;
        .add-icon{
          font-size: 50px;
        }
      }
    }
    .shop-item {
      flex: 1 0;
      .shop-content {
        display: flex;
        .l {
          flex: 0 0 200px;
          height: 192px;
          border: 2px solid #ddd;
          img {
            width: 80%;
            height: 100%;
            // border: 2px solid #ddd;
          }
        }
        .r {
          flex: 1 1 auto;
          ul {
            li {
              display: flex;
              background-color: #f3f3f3;
              align-items: center;
              &:nth-child(odd) {
                background-color: rgb(202, 202, 202);
              }
              p {
                padding: 15px 0;
                flex: 0 0 50%;
                border: 1px solid #fff;
              }
            }
          }
        }
      }
    }
    .vs {
      font-size: 40px;
      color: rgb(255, 0, 0);
      padding: 0 10px;
    }
    .shop-title {
      border: 2px solid #ddd;
      text-align: left;
      padding: 10px;
      .name {
        font-weight: bold;
        margin-bottom: 5px;
      }
      .desc {
        font-size: 12px;
      }
    }
  }
  .btn-box{
    padding: 10px 0;
    text-align: left;
  }
}
</style>
