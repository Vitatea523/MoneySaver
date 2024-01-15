<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style lang="less">
:root {
  --el-form-label-font-size: 30px;
}
.el-input {
  line-height: 50px !important;
  font-size: 16px;
  .el-input__inner {
    line-height: 50px !important;
    height: 50px !important;
    font-size: 16px;
  }
  .el-button {
    height: 50px !important;
    font-size: 16px;
  }
}
.el-form-item__label {
  font-size: 16px !important;
  line-height: 50px !important;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.page-title {
  font-size: 28px;
  line-height: 50px;
  margin: 20px;
}
</style>
<style lang="less" scoped>
body {
  color: #000;
  background-color: #333;
}
</style>
