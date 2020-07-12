<template>
    <a-comment>
        <template slot="actions">
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <a-icon type="like" :theme="action === 'liked' ? 'filled' : 'outlined'" @click="like(commonInfo.likes)" />
        </a-tooltip>
        <span style="padding-left: 8px;cursor: auto">
          {{ commonInfo.likes }}
        </span>
      </span>
            <span key="comment-basic-dislike">
        <a-tooltip title="Dislike">
          <a-icon
                  type="dislike"
                  :theme="action === 'disliked' ? 'filled' : 'outlined'"
                  @click="dislike"
          />
        </a-tooltip>
        <span style="padding-left: 8px;cursor: auto">
          {{ dislikes }}
        </span>
      </span>
        </template>
        <a slot="author">{{commonInfo.uname}}</a>
        <a-avatar
                slot="avatar"
                src="./src/assets/logo.png  "
                alt="用户头像"
        />
        <p slot="content">
            {{commonInfo.content}}
        </p>
        <a-tooltip slot="datetime" >
            <span>{{translate(commonInfo.ctime)}} - {{commonInfo.progress}}</span>
        </a-tooltip>
    </a-comment>

</template>

<script>
    import moment from 'moment';
    export default {
        name: 'Comment',

        props: ["commonInfo"],
        data() {
            return {
                likes: 0,
                dislikes: 0,
                action: null,
                moment,
            };
        },
        methods: {
            like(num) {
                this.likes = num + 1;
                this.action = 'liked';
            },
            dislike() {
                this.action = 'disliked';
            },
            translate(date) {
                return new Date(parseInt(date) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ');
            }
        }
    }
</script>

