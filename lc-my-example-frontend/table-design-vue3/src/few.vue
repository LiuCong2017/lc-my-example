<template>
  <DockMenu
      :items="items"
      :on-selected="handleSelected"
      :theme="{
      primary: '#b2b2b2',
      secondary: '#e5e5e5',
      tertiary: '#008ecc',
      textColor: '#000',
      textHoverColor: '#fff',
    }"
  />
  <router-view/>
</template>

<script>
import { DockMenu } from "vue-dock-menu";
import "vue-dock-menu/dist/vue-dock-menu.css";

export default {
  name: "App",
  components: {
    DockMenu,
  },
  setup() {
    const handleSelected = () => {};

    return {
      handleSelected,
    };
  },
  data() {
    return {
      count: 0,
      items: [
        {
          name: "file",
          menu: [
            { name: "New File" },
            { name: "New Window" },
            { name: "Open File" },
            { isDivider: true },
            {
              name: "Preferences",
              menu: [
                { name: "Settings" },
                {
                  name: "Themes",
                  menu: [
                    {
                      name: "White",
                      menu: [{ name: "white 1" }, { name: "white 2" }],
                    },
                    {
                      name: "Black",
                    },
                  ],
                },
              ],
            },
            { name: "Open Workspace" },
            { isDivider: true },
            { name: "Save" },
            { name: "Save As..." },
            { isDivider: true },
            { name: "Close" },
            { name: "Exit" },
          ],
        },
        {
          name: "edit",
          menu: [
            { name: "Undo" },
            { name: "Redo" },
            { isDivider: true },
            { name: "Cut" },
            { name: "Copy" },
            { name: "Paste" },
            { isDivider: true },
            { name: "Find" },
            { name: "Replace" },
          ],
        },
        {
          name: "View",
          menu: [
            { name: "Explorer" },
            { name: "Search" },
            { name: "Run" },
            { isDivider: true },
            {
              name: "Apperance",
              menu: [{ name: "Full Screen" }, { name: "Zen Mode" }],
            },
          ],
        },
        {
          name: "Help",
          menu: [{ name: "About" }, { name: "Check for Updates" }],
        },
      ],
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
