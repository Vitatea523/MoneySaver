<template>
  <div class="goods-page">
    <div v-show="!showDetail" :class="compareList.length > 0 ? 'compare' : ''">
      <div class="carousel-box">
        <div class="carousel">
          <el-carousel trigger="click" indicator-position="outside">
            <el-carousel-item v-for="item in imgList" :key="item">
              <img :src="item" alt="" />
            </el-carousel-item>
          </el-carousel>
        </div>
        <div class="tips">
          <span
            >Hot items in the pass 24 hours.</span
          >
        </div>
      </div>
      <div class="category-select">
        <el-select
          v-model="nowBrand"
          placeholder="Choose the brand"
          size="medium"
          @change="filterList"
          clearable
        >
          <el-option
            v-for="item in brandOption"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        <el-button
          class="refresh-btn"
          type="danger"
          @click="refresh"
          >Refresh</el-button
        >

      </div>

      <div class="box">
        <div
          class="goods"
          v-for="item in showList"
          :key="item.productId"
          @click="showDetailEvent(item.productId)"
        >
          <div class="good-item">
            <el-image :src="item.picture" fit="fill" class="good-img">
              <template #error>
                <div class="image-slot">
                  <img src="@images/product01.jpg" alt="" />
                </div>
              </template>
            </el-image>
            <p class="name">{{ item.productName }}</p>
            <p class="currentPrice">Now${{ item.currentPrice }}</p>
            <p class="orginalPrice">Was${{ item.orginalPrice }}</p>
            <p class="brand">Brand:{{ item.brand }}</p>
            <p class="category">
              Category: <span>{{ item.category }}</span>
            </p>
            <div class="operate-box" @click.stop="">
              <el-checkbox
                @change="compareEvent(item)"
                v-model="item.compare"
                :disabled="item.compareDisabled"
                label="Compare"
                size="small"
              ></el-checkbox>
            </div>
          </div>
        </div>
      </div>
      <div class="compare-box" v-show="compareList.length > 0">
        <div class="title">Compare Bar</div>
        <div class="compare-operate">
          <div class="compare-goods">
            <div class="goods" v-for="item in compareList" :key="item.name">
              <img :src="item.picture" alt="" />
              <div class="info">
                <p class="name">{{ item.productName }}</p>
                <p class="price">${{ item.currentPrice }}</p>
              </div>
            </div>
          </div>
          <div class="compare-btn">
            <el-button @click="jumpCompare" type="primary">Compare</el-button>
          </div>
        </div>
      </div>
    </div>
    <div class="detail-box" v-if="showDetail">
      <div class="detail-back">
        <el-button type="danger" @click="hideDetailEvent">Back</el-button>
      </div>
      <shopDetail v-if="showDetail" :goodsId="goodsId"></shopDetail>
    </div>
  </div>
</template>

<script>
import shopDetail from "./shop-detail.vue";
export default {
  components: {
    shopDetail,
  },
  props: {
    search: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      imgList: [
        require("@images/banner01.jpg"),
        require("@images/banner02.jpg"),
        require("@images/banner03.jpg"),
        require("@images/banner04.jpg"),
        require("@images/banner05.jpg"),
      ],
      nowBrand: "",
      brandOption: [],
      goodsId: "",
      list: [],
      showList: [],
      compareList: [],
      showDetail: false,
    };
  },
  created() {
    const query = this.$route.query;
    if (query && query.type === "productDetail" && query.id) {
      this.goodsId = query.id;
      this.showDetail = true;
    }
    this.getList();
    this.getBrandList();
  },
  methods: {
    // 刷新
    refresh() {
      this.delete();
      this.getFromAPI();
      this.getList();
    },
    delete() {
      this.$axios.delete("/product/delete").then(
        (res) => {
          console.log(res, "deletesuccess");
        },
        (err) => {
          console.log(err, "err");
        }
      );
    },
    getFromAPI() {
      this.$axios.get("/product/api").then(
        (res) => {
          console.log(res, "apisuccess");
        },
        (err) => {
          console.log(err, "err");
        }
      );
    },
    // 获取brand列表
    getBrandList() {
      this.$axios.get("/product/brands").then(
        (res) => {
          const data = res.data;
          this.brandOption = [];
          // 循环赋值
          data.forEach((item) => {
            this.brandOption.push({
              label: item,
              value: item,
            });
          });
          console.log(res);
        },
        (err) => {
          console.log(err);
        }
      );
    },
    filterList() {
      this.showList = this.list.filter((item) => {
        const brand = item.brand.indexOf(this.nowBrand) >= 0;
        if (brand) {
          return true;
        }
      });
    },
    showDetailEvent(id) {
      this.goodsId = id;
      this.showDetail = true;
    },
    hideDetailEvent() {
      this.goodsId = "";
      this.showDetail = false;
    },
    getList() {
      this.$axios.get("/product/sorted").then(
        (res) => {
          const data = res.data || [];
          this.showList = data;
          this.list = data;
        },
        (err) => {
          console.log(err, "err");
          const res = {
            code: 200,
            message: "success",
            data: [
              {
                productName: "Ronald White",
                brand: "dior",
                category: "lipstick",
                orginalPrice: 1080,
                currentPrice: 1411,
                picture:
                  "https://cdn.shopify.com/s/files/1/1338/0845/collections/lippie-pencil_grande.jpg?v=1512588769",
              },
              {
                productName: "Nancy Lewis",
                brand: "dior",
                category: "lipstick",
                orginalPrice: 718,
                currentPrice: 4655,
                picture: "mid://oowcr.com/xejmno",
              },
              {
                productName: "Sandra Thompson1",
                brand: "dior",
                category: "123456",
                orginalPrice: 541,
                currentPrice: 2436,
                picture: "http://mfvu.ml/ighzmfi",
              },
              {
                productName: "Sandra Thompson2",
                brand: "dior",
                category: "666",
                orginalPrice: 541,
                currentPrice: 2436,
                picture: "http://mfvu.ml/ighzmfi",
              },
              {
                productName: "Sandra Thompson3",
                brand: "dior",
                category: "666",
                orginalPrice: 541,
                currentPrice: 2436,
                picture: "http://mfvu.ml/ighzmfi",
              },
            ],
          };
          this.showList = res.data;
          this.list = res.data;
        }
      );
    },
    compareEvent(item) {
      if (this.compareList.length >= 2 && item.compare) {
        item.compare = false;
        this.$message({
          message: "You can only choose two items",
          type: "error",
        });
      }
      if (item.compare && this.compareList.length < 2) {
        this.compareList.push(item);
      }
      if (!item.compare) {
        let i = "";
        this.compareList.some((c, index) => {
          if (c.productName === item.productName) {
            i = index;
            return true;
          }
        });
        if (i !== "") {
          this.compareList.splice(i, 1);
        }
      }
    },
    jumpCompare() {
      localStorage.setItem("goods1", JSON.stringify(this.compareList[0]));
      localStorage.setItem("goods2", JSON.stringify(this.compareList[1]));
      this.$emit("changePage", "COMPARISON");
    },
  },
  watch: {
    search: {
      handler(newVal) {
        this.showList = this.list.filter((item) => {
          const brand = item.brand.indexOf(newVal) >= 0;
          const category = item.category.indexOf(newVal) >= 0;
          const productName = item.productName.indexOf(newVal) >= 0;
          if (brand || category || productName) {
            return true;
          }
        });
      },
    },
  },
};
</script>
<style lang="less">
.goods-page {
  .good-img {
    height: 270px;
    img {
      height: 230px;
      width: 90%;
    }
  }
}
</style>
<style lang="less" scoped>
.goods-page {
  position: relative;
  .carousel-box {
    margin-bottom: 20px;
    .carousel {
      height: 380px;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .tips {
      margin-top: 0;
      border: 1px solid #ddd;
      text-align: left;
      padding: 20px;
      display: flex;
      align-items: center;
      img {
        width: 30px;
        margin-right: 15px;
      }
      span {
        font-weight: bold;
        font-size: 30px;
      }
    }
  }
  .category-select {
    text-align: left;
    margin-bottom: 75px;
    .refresh-btn {
      margin-top: 5px;
      float: right;
    }
    .advertising {
      width: 600px;
      height: 200px;
      position: absolute;
      left: 50%;
      margin-left: -150px;
      background-image: -webkit-linear-gradient(
        left,
        #fd3c3c,
        #fda539 10%,
        #cc00ff 20%,
        #cc00cc 30%,
        #ccccff 40%,
        #00ffff 50%,
        #ccccff 60%,
        #cc00cc 70%,
        #cc00ff 80%,
        #66ffff 90%,
        blue 100%
      );
      -webkit-text-fill-color: transparent;
      -webkit-background-clip: text;
      -webkit-background-size: 200% 100%;
      -webkit-animation: masked-animation 4s linear infinite;
      font-size: 50px;
    }
  }
  .box {
    display: flex;
    flex-wrap: wrap;
    .goods {
      flex: 0 1 25%;
      width: 25%;
      padding-right: 30px;
      margin-bottom: 20px;
      cursor: pointer;
      .good-item {
        padding: 20px;
        transition: 0.5s ease;
        border-radius: 5px;
        &:hover {
          box-shadow: 0px 0px 20px #b4b4b4;
          transform: scale(1.02);
        }
      }

      .operate-box {
        margin: 15px 0;
        border: 1px solid #ddd;
        display: flex;
        padding: 2px 10px;
      }
      &:nth-child(4n) {
        padding-right: 0;
      }
      .name {
        line-height: 30px;
        font-size: 23px;
        padding: 5px 0;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .brand {
        padding: 5px 0;
      }
      .category {
        padding: 5px 0;
        span {
          color: rgb(184, 184, 184);
        }
      }
      .currentPrice {
        font-size: 23px;
        padding: 10px 0;
      }
      .orginalPrice {
        padding: 5px 0;
      }
    }
  }
  .compare {
    padding-bottom: 180px;
  }
  .compare-box {
    position: fixed;
    bottom: 0;
    left: 50%;
    margin-left: -40%;
    border: 1px solid #ddd;
    background-color: #fff;
    border-radius: 5px;
    width: 80%;
    z-index: 1;
    box-shadow: 0 0 2px rgb(105, 105, 105);
    .title {
      line-height: 30px;
      text-align: left;
      padding: 10px 20px 0;
    }
    .compare-operate {
      display: flex;
      align-items: center;
      padding-right: 15px;
    }
    .compare-goods {
      display: flex;
      padding: 15px 20px 0;
      flex: 1 1 auto;
      flex-wrap: wrap;
      .goods {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
        img {
          width: 80px;
          height: 80px;
          border: 1px solid #ddd;
        }
        .info {
          text-align: left;
          padding: 0 10px;
          .name {
            font-size: 16px;
            line-height: 30px;
          }
          .price {
            font-size: 14px;
          }
        }
      }
    }
  }
  .detail-back {
    text-align: left;
    margin-bottom: 10px;
  }
}
</style>

