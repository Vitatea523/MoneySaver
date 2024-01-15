<template>
  <div class="shop-page">
    <div class="r">
      <div class="carousel">
        <el-carousel trigger="click" indicator-position="outside">
          <el-carousel-item v-for="item in imgList" :key="item">
            <img src="@images/banner01.jpg" alt="" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="tips">
        <img src="@images/logo.png" alt="" />
        <span
          >Hot items in the system and the number of items with reduced prices
          in the pase 24 hours.</span
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgList: ['','','',''],
    };
  },
  created() {
    this.getImgList()
  },
  methods: {
    getImgList() {
      this.$axios
        .post("/api/banner", {
        })
        .then(
          (res) => {
            const data = res.data
            this.imgList = data
            console.log(res);
          },
          (err) => {
            console.log(err);
          }
        );
    },
  },
};
</script>

<style lang="less" scoped>
.shop-page {
  display: flex;
  .l {
    flex: 0 0 250px;
    .navs {
      display: flex;
      align-items: center;
      background-color: rgb(116, 116, 116);
      border: 1px solid rgb(102, 102, 102);
      text-align: left;
      .nav-title {
        flex: 0 0 100px;
        color: #fff;
        padding: 0 10px;
        line-height: 24px;
        font-size: 16px;
      }
      .nav-item {
        flex: 1 1 auto;
        background-color: #d6d6d6;
        font-size: 12px;
        padding: 10px 0;
        p {
          padding: 5px 10px;
          cursor: pointer;
        }
      }
    }
  }
  .r {
    flex: 1 1 auto;
    padding-left: 10px;
    .carousel {
      min-height: 330px;
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
      }
    }
  }
}
</style>
