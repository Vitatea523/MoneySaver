<template>
  <div class="coupon-page">
    <div class="coupon-select">
      <el-select
        v-model="nowStore"
        placeholder="Choose the store"
        @change="filterList"
        clearable
      >
        <el-option
          v-for="item in storeOption"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-button class="refresh-btn" type="danger" @click="refresh" :loading="refreshLoad"
        >Refresh</el-button
      >
    </div>
    <div class="box">
      <div class="item" v-for="item in showList" :key="item.id">
        <a :href="item.link" target="_blank" rel="noopener noreferrer">
          <img :src="'https://logo.clearbit.com/'+item.link" alt="" />
           <!--<img :src="item.picture" alt="" />-->
        </a>
        <div class="title" :title="item.title">{{ item.title }}</div>
        <div class="store">{{ item.store }}</div>
        <div class="date">
          <span>{{ item.startDate }}</span>
          ~
          <span>{{ item.endDate }}</span>
        </div>
        <div class="category" :title="item.productCategory">{{ item.productCategory }}</div>
        <div class="code">code:{{ item.code || "-" }}</div>
        <!-- <div class="link">
          <a :href="item.link" target="_blank" rel="noopener noreferrer">{{
            item.link
          }}</a>
        </div>-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      showList: [],
      nowStore: "",
      storeOption: [
        {
          label: "amazon-com",
          value: "amazon-com",
        },
        {
          label: "awin",
          value: "awin",
        },
        {
          label: "rakuten",
          value: "rakuten",
        },
      ],
      refreshLoad: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 刷新
    refresh() {
      this.refreshLoad = true
      this.$axios.post("/coupon/updateCouponDatabase").then(
          (res) => {
            console.log(res, "couponDeleteSuccess");
          },
          (err) => {
            console.log(err, "couponDeleteErr");
          }
      )
      this.getList();
    },
    // 下拉列表过滤
    filterList() {
      this.showList = this.list.filter((item) => {
        const store = item.store.indexOf(this.nowStore) >= 0;
        if (store) {
          return true;
        }
      });
    },

    getList() {
      this.$axios.get("/coupon/listCoupon").then(
        (res) => {
          const data = res.data || [];
          this.list = data;
          this.showList = data;
          this.refreshLoad = false
        },
        (err) => {
          console.log(err, "err");
          const res = {
            result: true,
            message: "success",
            data: [
              {
                title: "Save 60%",
                productCategory: "Patio, Lawn & Garden",
                code: "60QR59AX",
                store: "amazon-com",
                link: "https://www.amazon.com/",
                start_date: "2021-10-12",
                end_date: "2021-10-13",
                description:
                  "Valid on select products from ZYCZFC, through 10/12 while supplies last.",
                picture:
                  "https://img.22.cn/show//userfiles2014/image/zixun/20171130_6364766190980806805049044.jpeg",
              },
              {
                title: "Save 60%",
                productCategory: "Patio, Lawn & Garden",
                code: "60QR59AX",
                store: "amazon-com",
                link: "https://www.amazon.com/",
                start_date: "2021-10-12",
                end_date: "2021-10-13",
                description:
                  "Valid on select products from ZYCZFC, through 10/12 while supplies last.",
                picture:
                  "https://img.22.cn/show//userfiles2014/image/zixun/20171130_6364766190980806805049044.jpeg",
              },
              {
                title: "Save 70%",
                productCategory: "Clothing & Accessories",
                code: "70YN91KO",
                store: "amazon-com",
                link: "https://www.amazon.com/",
                start_date: "2021-10-12",
                end_date: "2021-10-30",
                description:
                  "Valid on select products from FastDeng, through 10/29 while supplies last.",
                picture:
                  "https://img.22.cn/show//userfiles2014/image/zixun/20171130_6364766190980806805049044.jpeg",
              },
              {
                title: "Free Delivery with Nike+!",
                productCategory:
                  "Men's Outerwear,Men's Sportswear,Men's Footwear,Women's Footwear,Women's Sportswear,Women's Outerwear,Children's Footwear",
                code: "",
                store: "awin",
                link: "http://www.nike.com",
                start_date: "2020-06-18",
                end_date: "2021-10-31",
                description:
                  "Get free standard delivery when you sign up to of member service - Nike+. T&Cs apply, please see website for more detail",
                picture:
                  "https://brandlogos.org/placeholder/?brand=nike.com&size=[size]&format=[format]&token=[token]&domain=[domain]",
              },
              {
                title:
                  "Extra 20% off Men's Final Markdowns at JoesNewBalanceOutlet.com!",
                productCategory: "",
                code: "",
                store: "rakuten",
                link: "https://joesnewbalanceoutlet.com/",
                start_date: "2019-09-30",
                end_date: "2025-09-29",
                description: "",
                picture:
                  "https://brandlogos.org/placeholder/?brand=joesnewbalanceoutlet.com&size=[size]&format=[format]&token=[token]&domain=[domain]",
              },
              {
                title:
                  "Extra 20% off Men's Final Markdowns at JoesNewBalanceOutlet.com!",
                productCategory: "",
                code: "",
                store: "rakuten",
                link: "https://joesnewbalanceoutlet.com/",
                start_date: "2019-09-30",
                end_date: "2025-09-29",
                description: "",
                picture:
                  "https://brandlogos.org/placeholder/?brand=joesnewbalanceoutlet.com&size=[size]&format=[format]&token=[token]&domain=[domain]",
              },
            ],
          };
          this.list = res.data;
          this.showList = res.data;
          this.refreshLoad = false
        }
      );
    },
  },
};
</script>

<style lang="less" scoped>
.coupon-page {
  .refresh-btn {
    margin: 0 10px;
    float: right;
  }
  .coupon-select {
    text-align: left;
    margin-bottom: 10px;
  }
  .box {
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
    font-size: 20px;
    line-height: 26px;
    .item {
      flex: 0 1 25%;
      width: 25%;
      padding-right: 16px;
      margin-bottom: 20px;
      &:nth-child(4n) {
        padding-right: 0;
      }
      img {
        height: 180px;
        width: 100%;
        border: 1px solid #ddd;
      }
      .store,.title {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        font-size: 22px;
        line-height: 28px;
      }
      .store{
        color: #777;
        font-size: 20px;
      }
      .date {
        color: #333;
        font-size: 14px;
      }
      .category {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        font-size: 14px;
      }
      .code {
        font-size: 18px;
        color: rgb(255, 0, 0);
        line-height: 30px;
      }
      .link {
        line-height: 20px;
        a {
          font-size: 14px;
          color: rgb(28, 88, 250);
          text-decoration: none;
        }
      }
    }
  }
}
</style>
