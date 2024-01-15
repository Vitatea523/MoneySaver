<template>
  <div class="header">
   <div class="title">
      <span>Money Saver</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<style lang="less" scoped>
  .header {
    padding: 40px 0;
    background-color:  #e85356;
    text-align: left;
    span {
      // font-family: fangsong;
      // font-size: 45px;
      // color: #fff;
      // font-weight: bold;
      // padding-left: 50px;
      flex: 0 1 400px;
      text-align: left;
      padding: 10px 0px 0px 30px;
      font-weight: bold;
      font-size: 40px;
      font-family: Tahoma, Geneva, sans-serif;
      background-image: -webkit-linear-gradient(left, #ffffff 0%, #f3a693 100%);
      -webkit-text-fill-color: transparent; /* 将字体设置成透明色 */
      -webkit-background-clip: text; /* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
      -webkit-background-size: 200% 100%;
      -webkit-animation: masked-animation 4s linear infinite;
    }
  }
</style>
