<template>
  <div class="shop-detail-page">
    <div class="breadcrumb">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>{{ goods.category }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{ goods.brand }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="shop-box">
      <div class="l">
        <el-image :src="goods.picture" fit="fill">
              <template #error>
                <div class="image-slot">
                  <img src="@images/product01.jpg" alt="" />
                </div>
              </template>
            </el-image>
      </div>
      <div class="r">
        <div class="s-title">
          <p class="name">{{ goods.productName }}</p>
          <!--
          <p class="desc">
            REFILL INSERT-ALL SKIN TYPES-REGENERATING,BRIGHTENING
          </p>-->
        </div>
        <div class="price-box">
          <div class="now-price">Now {{ goods.currentPrice }}$</div>
          <div class="coupon">
            <!-- <p class="coupon-price">5$ coupon</p> -->
            <p class="was-price">was {{ goods.orginalPrice }}$</p>
          </div>
          <div class="shop-info">
            <div
              class="shop-num"
              :class="{ active: this.like }"
              @click="likeEvent"
            >
              <img src="@images/dianzan.png" alt="" />
              <span>{{ this.likeNumber }}</span>
            </div>
          </div>
        </div>
        <div class="base-box">
          <div class="date">Category:{{ goods.category }}</div>
          <div class="btns-box">
            <el-button
              :loading="favoritesLoad"
              :type="favoritesType"
              @click="favoriteEvent"
              >{{ favoritesText }}</el-button
            >
          </div>
        </div>
        <div class="base-box">
          <div class="date">Brand:{{ goods.brand }}</div>
        </div>
      </div>
    </div>

    <div class="description">Description: {{ goods.description }}</div>

    <div class="comment-box">
      <div class="num">{{ commentList.length }} comments</div>
      <div class="">
        <div class="new-comment">
          <el-input
            v-model="textarea"
            :rows="5"
            type="textarea"
            placeholder="post new comment"
          />
          <div class="submit-btn">
            <el-button
              type="primary"
              round
              @click="commoentEvent"
              :loading="commoentLoad"
              >submit</el-button
            >
          </div>
        </div>
        <div class="list" v-for="item in commentList" :key="item.commentId">
          <div class="avatar">
            <el-avatar :size="50" icon="el-icon-user-solid"></el-avatar>
          </div>
          <span class="content">
            {{ item.content }}
          </span>
          <span class="name">AnonymousUser</span>
          <span class="date">{{ item.time }}</span>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    goodsId: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      textarea: "",
      goods: {},
      commentList: [
        {
          content: "nice",
          time: "2020-12-30 12:55:55",
        },
      ],
      likeNumber: 0,
      collect: false,
      like: false,
      favoritesText: "Add to favorites",
      favoritesType: "success",
      favoritesLoad: false,
      commoentLoad: false,
    };
  },
  created() {
    this.getDetail();
  },
  methods: {
    commoentEvent() {
      if (!this.textarea) {
        this.$message({
          message: "commoent is empty",
          type: "error",
        });
        return;
      }
      this.commoentLoad = true;
      this.$axios
        .post("/product/addComment", {
          productId: this.goodsId,
          content: this.textarea,
        })
        .then(
          (res) => {
            this.textarea = "";
            this.commoentLoad = false;
            this.getDetail();
            console.log(res);
          },
          (err) => {
            this.textarea = "";
            this.commoentLoad = false;
            this.getDetail();
            console.log(err);
          }
        );
    },
    likeEvent() {
      var token = window.sessionStorage.getItem("token");
      var request = "/product/like/" + token + "/" + this.goodsId;
      this.$axios.get(request).then(
        (res) => {
          if (this.like) {
            this.likeNumber--;
          } else {
            this.likeNumber++;
          }
          this.like = !this.like;
          console.log(res);
        },
        (err) => {
          if (this.like) {
            this.likeNumber--;
          } else {
            this.likeNumber++;
          }
          this.like = !this.like;
          console.log(err);
        }
      );
    },
    favoriteEvent() {
      console.log(this.collect);
      if (this.collect) {
        this.cancelCollect();
      } else {
        this.addCollect();
      }
      this.collect = !this.collect;
    },
    addCollect() {
      this.favoritesLoad = true;
      this.$axios
        .post("/product/collect", {
          userId: window.sessionStorage.getItem("token"),
          productId: this.goodsId,
        })
        .then(
          (res) => {
            this.favoritesText = "Delete favorite";
            this.favoritesType = "danger";
            this.favoritesLoad = false;
            console.log(res);
          },
          (err) => {
            console.log(err);
          }
        );
    },
    cancelCollect() {
      this.favoritesLoad = true;
      this.$axios
        .delete("/product/cancelCollect", {
          data: {
            userId: window.sessionStorage.getItem("token"),
            productId: this.goodsId,
          },
        })
        .then(
          (res) => {
            this.favoritesText = "Add to favorites";
            this.favoritesType = "success";
            this.favoritesLoad = false;
            console.log(res);
          },
          (err) => {
            console.log(err);
          }
        );
    },
    getDetail() {
      var token = window.sessionStorage.getItem("token");
      var request = "/product/" + token + "/" + this.goodsId;
      this.$axios.get(request).then(
        (res) => {
          console.log(res.data.products);
          // const detail = res.data.products;
          this.goods = res.data.products;
          this.commentList = res.data.commentList;
          this.likeNumber = res.data.likeNumber;
          this.collect = res.data.collect;
          if (this.collect) {
            this.favoritesText = "Delete favorite";
            this.favoritesType = "danger";
          }
          this.like = res.data.like;
        },
        (err) => {
          // this.favoritesType = this.goods.collect ? "danger" : "success";
          // console.log(this.goods, "this.goods");
        }
      );
    },
  },
};
</script>

<style lang="less" scoped>
.shop-detail-page {
  .breadcrumb {
    background-color: #f3f3f3;
    padding: 10px 20px;
  }
  .shop-box {
    padding: 10px 0;
    display: flex;
    .l {
      flex: 0 0 300px;
      height: 300px;
      img {
        height: 100%;
        width: 100%;
        border: 3px solid #ddd;
      }
    }
    .r {
      flex: 1 1 auto;
      padding-left: 10px;
      .s-title {
        border: 2px solid #ddd;
        text-align: left;
        line-height: 40px;
        padding: 0 10px;
        height: 40px;
        .name {
          font-weight: bold;
          margin-bottom: 5px;
        }
        .desc {
          font-size: 12px;
        }
      }
      .price-box {
        padding: 10px 0;
        display: flex;
        align-items: center;
        .now-price {
          width: 110px;
          height: 46px;
          line-height: 46px;
          font-weight: bold;
          border-radius: 6px;
          font-size: 20px;
          color: rgb(255, 0, 0);
          margin-right: 10px;
        }
        .coupon {
          margin-right: 10px;
          .coupon-price {
            padding: 6px;
            background-color: rgb(255, 0, 0);
            color: #fff;
            font-size: 16px;
          }
          .was-price {
            margin-top: 5px;
            font-weight: bold;
          }
        }
        .shop-info {
          flex: 1 1 auto;
          text-align: right;
          line-height: 26px;
          .shop-num {
            align-items: center;
            display: inline-block;
            background-color: #f2f2f2;
            padding: 5px 10px;
            &.active {
              img {
                opacity: 1;
              }
            }
            img {
              width: 15px;
              height: 15px;
              position: relative;
              top: 2px;
              cursor: pointer;
              opacity: 0.2;
            }
          }
          span {
            margin-left: 5px;
            font-size: 14px;
          }
        }
      }
      .base-box {
        display: flex;
        align-items: center;
        justify-content: space-between;
        min-height: 50px;
      }
    }
  }
  .description {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    text-align: left;
    margin: 15px 0;
    line-height: 24px;
    padding: 10px 0;
    text-indent: 2em;
  }
  .comment-box {
    margin: 10px 0;
    padding: 10px;
    background-color: #f3f3f3;
    .num {
      text-align: left;
    }
    .new-comment {
      padding: 10px 0;
    }
    .list {
      display: flex;
      align-items: center;
      background-color: #fff;
      padding: 10px 20px;
      text-align: left;
      font-size: 18px;
      line-height: 20px;
      .name {
        text-align: right;
        flex: 0 0 200px;
        padding: 0 10px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .content {
        flex: 1 1 auto;
        word-break: break-all;
        padding: 0 0 0 20px;
      }
      .avatar {
        text-align: right;
        flex: 0 0 auto;
        padding: 0 0 0 5px;
      }
      .date {
        text-align: right;
        flex: 0 0 200px;
      }
    }
    .submit-btn {
      margin: 20px 0 10px;
      text-align: right;
    }
  }
}
</style>
