(function(t){function i(i){for(var a,r,o=i[0],c=i[1],u=i[2],m=0,p=[];m<o.length;m++)r=o[m],Object.prototype.hasOwnProperty.call(e,r)&&e[r]&&p.push(e[r][0]),e[r]=0;for(a in c)Object.prototype.hasOwnProperty.call(c,a)&&(t[a]=c[a]);l&&l(i);while(p.length)p.shift()();return s.push.apply(s,u||[]),n()}function n(){for(var t,i=0;i<s.length;i++){for(var n=s[i],a=!0,o=1;o<n.length;o++){var c=n[o];0!==e[c]&&(a=!1)}a&&(s.splice(i--,1),t=r(r.s=n[0]))}return t}var a={},e={app:0},s=[];function r(i){if(a[i])return a[i].exports;var n=a[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,r),n.l=!0,n.exports}r.m=t,r.c=a,r.d=function(t,i,n){r.o(t,i)||Object.defineProperty(t,i,{enumerable:!0,get:n})},r.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},r.t=function(t,i){if(1&i&&(t=r(t)),8&i)return t;if(4&i&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(r.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&i&&"string"!=typeof t)for(var a in t)r.d(n,a,function(i){return t[i]}.bind(null,a));return n},r.n=function(t){var i=t&&t.__esModule?function(){return t["default"]}:function(){return t};return r.d(i,"a",i),i},r.o=function(t,i){return Object.prototype.hasOwnProperty.call(t,i)},r.p="";var o=window["webpackJsonp"]=window["webpackJsonp"]||[],c=o.push.bind(o);o.push=i,o=o.slice();for(var u=0;u<o.length;u++)i(o[u]);var l=c;s.push([0,"chunk-vendors"]),n()})({0:function(t,i,n){t.exports=n("56d7")},"034f":function(t,i,n){"use strict";var a=n("85ec"),e=n.n(a);e.a},"56d7":function(t,i,n){"use strict";n.r(i);n("e260"),n("e6cf"),n("cca6"),n("a79d");var a=n("2b0e"),e=function(){var t=this,i=t.$createElement,n=t._self._c||i;return n("div",{staticClass:"app-wrapper",attrs:{id:"app"}},[n("div",{staticClass:"player-wrapper"},[n("div",{staticClass:"container-player"},[n("div",{class:"container-music-info "+t.playerStatus},[n("div",{staticClass:"container-music-name"},[t._v(t._s(t.musicInfo.name))]),n("div",{staticClass:"container-artist-name"},[t._v(t._s(t.musicInfo.artist))]),n("div",{staticClass:"container-music-time"},[n("div",{staticClass:"container-music-current-time"},[t._v(" "+t._s(t.musicInfo.current)+" ")]),n("div",{staticClass:"container-music-duration"},[t._v(" "+t._s(t.musicInfo.durationFormat)+" ")])]),n("div",{staticClass:"container-music-duration-flow",on:{mousemove:t.showInstantTime,mouseout:t.hideInstantTime,click:t.setProgress}},[n("div",{staticClass:"container-music-instant-time",style:t.instantTimeStyle},[t._v(" "+t._s(t.instantTimeFormat)+" ")]),n("div",{staticClass:"container-control-hover",style:{width:t.hoverWidth}}),n("div",{staticClass:"container-music-active-bar",style:{width:t.activeLength}})])]),n("div",{staticClass:"container-player-main"},[n("div",{class:"container-album "+t.playerStatus},[n("img",{class:"container-music-image "+t.playerStatus,attrs:{src:t.musicImage,alt:"歌曲图片"}}),n("div",{staticClass:"container-dot"})]),n("div",{staticClass:"container-controls"},[n("i",{staticClass:"control-btn fa fa-backward",on:{click:t.playControl}}),n("i",{class:"control-btn fa "+t.playIcon,on:{click:function(i){return t.playControl("play")}}}),n("i",{staticClass:"control-btn fa fa-forward",on:{click:t.playControl}})])])])])])},s=[],r=(n("b0c0"),n("d3b7"),n("ac1f"),n("5319"),{name:"app",data:function(){return{mid:868773387,musicImage:"https://p2.music.126.net/iAaRt_l_ussYqkLVuDEpqg==/2931298001578529.jpg",musicInfo:{url:"",name:"走在冷风中",artist:"周二珂",current:"00:00",duration:0,durationFormat:"00:00"},activeLength:"0%",musicStatus:"pause",audio:new Audio,hoverWidth:"0%",instantTimeStyle:"",instantTimeFormat:"00:00",musicProgress:0}},computed:{playerStatus:function(){return"play"===this.musicStatus?"active":""},playIcon:function(){return"pause"===this.musicStatus?"fa-play":"fa-pause"}},methods:{playControl:function(t){"play"===t?"pause"===this.musicStatus?(this.audio.play(),this.musicStatus="play"):(this.audio.pause(),this.musicStatus="pause"):this.fetchMusic()},timeFormat:function(t){var i=Math.floor(t/60),n=Math.floor(t-60*i);return i<10&&(i="0"+i),n<10&&(n="0"+n),i+":"+n},fetchMusic:function(){var t=this,i=window.location.host;fetch("http://"+i+"/getMusicUrl").then((function(t){return t.text()})).then((function(i){var n=JSON.parse(i).data;t.musicInfo.url=n.url.replace("http://","https://"),t.musicInfo.name=n.name,t.musicInfo.artist=n.artistsname,t.musicImage=n.picurl.replace("http://","https://"),t.audio.src=t.musicInfo.url,t.audio.onloadedmetadata=function(){t.musicInfo.durationFormat=t.timeFormat(t.audio.duration),t.musicInfo.duration=t.audio.duration},"play"===t.musicStatus&&setTimeout((function(){t.audio.play()}),300)}))},showInstantTime:function(t){this.hoverWidth=t.layerX+"px",this.musicProgress=this.musicInfo.duration*(t.layerX/t.target.offsetWidth),this.instantTimeFormat=this.timeFormat(this.musicProgress),this.instantTimeStyle="left:"+t.layerX+"px;display:block;margin-left:-21px"},hideInstantTime:function(){this.instantTimeStyle="",this.hoverWidth="0px",this.musicProgress=0},setProgress:function(){this.audio.currentTime=this.musicProgress}},mounted:function(){var t=this;this.audio.loop=!1,this.fetchMusic(),this.audio.ontimeupdate=function(){var i=t.audio.currentTime,n=i/t.musicInfo.duration*100;isNaN(i)?t.musicInfo.current="00:00":(t.musicInfo.current=t.timeFormat(i),t.activeLength=n+"%"),100===n&&(t.activeLength="0%",t.fetchMusic(),t.musicInfo.current="00:00")}}}),o=r,c=(n("034f"),n("2877")),u=Object(c["a"])(o,e,s,!1,null,null,null),l=u.exports;a["a"].config.productionTip=!1,new a["a"]({render:function(t){return t(l)}}).$mount("#app")},"85ec":function(t,i,n){}});