<template>
  <div class="market-page">
    <div class="box">
      <div class="market" v-for="item in list" :key="item.id">
        <div class="title">{{ item.title }}</div>
        <div class="content">{{ item.desc }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          id: 1,
          title: "Amazon",
          desc: " Amazon is an American multinational technology company which focuses on e-commerce, cloud computing, digital streaming, and artificial intelligence. It is one of the Big Five companies in the U.S. information technology industry, along with Google, Apple, Microsoft, and Facebook. The company has been referred to as one of the most influential economic and cultural forces in the world, as well as the world's most valuable brand",
        },
        {
          id: 2,
          title: "Awin",
          desc: " Awin ist der weltweit größte Anbieter für Affiliate-Marketing und verbindet als Netzwerk Advertiser und Publisher weltweit. Awin vermarktet für rund 15.200 internationale Werbekunden Produkte und Dienstleistungen im Internet und kooperiert dafür mit über 211.000 aktiven Vertriebspartnern. Damit erreicht das Netzwerk nach eigenen Angaben Endkunden in über 180 Ländern der Welt. Zu den größten Werbekunden des Unternehmens gehören in Deutschland Kunden wie Telekom, 1&1, Opodo, die Volkswagen Bank und Postbank, ASOS und Douglas.",
        },
        {
          id: 3,
          title: "Rakuten",
          desc: "Rakuten Group, Inc. (楽天グループ株式会社, Rakuten Group Kabushiki-gaisha), is a Japanese electronic commerce and online retailing company based in Tokyo, founded by Hiroshi Mikitani in 1997. Centered around Rakuten Ichiba, its business-to-many EC platform and one of the largest e-commerce sites in Japan, its businesses include financial services utilizing fintech, as well as digital content and communications services such as messaging app Viber, e-book distributor Kobo, and Japan's fourth mobile carrier Rakuten Mobile.[2] Rakuten has 14,826 employees worldwide, operating in 29 countries and regions, and its revenues totaled US$7.2 billion with operating profits of about US$347.9 million as of 2016.[3][4] It is often referred to as the Amazon of Japan",
        },
      ],
    };
  },
  created() {},
  methods: {},
};
</script>

<style lang="less" scoped>
.market-page {
  padding: 20px 40px;
  .box {
    .market {
      text-align: left;
      padding: 10px;
      .title {
        font-size: 20px;
        font-weight: bold;
      }
      .content {
        line-height: 24px;
        padding: 10px 0;
        letter-spacing: 2px;
        text-indent: 2em;
      }
    }
  }
}
</style>