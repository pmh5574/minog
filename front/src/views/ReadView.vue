<script setup lang="ts">
import { onMounted } from 'vue';
import axios from 'axios';
import {ref} from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
    postId: {
        type: [Number, String],
        require: true,
    }
});

const post = ref({
    id: 0,
    title: "",
    content: "",
});

onMounted(() => {
    axios.get(`/api/posts/${props.postId}`).then((response) => {
        post.value = response.data;
    });
});

const router = useRouter();

const moveToEdit = () => {
    router.push({ name : "edit", params: { postId : props.postId } });
};
</script>

<template>
    <el-row>
        <el-col>
            <h2 class="title">{{ post.title }}</h2>

            <div class="sub">
                <div class="sub d-flex">
                    <div class="category">개발</div>
                    <div class="regDate">2023-04-20</div>
                </div>
            </div>
        </el-col>
    </el-row>
    <el-row>
        <el-col>
            <div class="content">{{ post.content }}</div>
        </el-col>
    </el-row>

    <el-row>
        <el-col>
            <div class="d-flex justify-content-end">
                <el-button type="warning" @click="moveToEdit()">수정</el-button>
            </div>
        </el-col>
    </el-row>
</template>

<style scoped lang="scss">
.title {
    font-size: 1.6rem;
    font-weight: 600;
    color: #383838;
    margin: 0;
}
.content {
    font-size: 0.95rem;
    margin-top: 12px;
    color: #616161;
    white-space: break-spaces;
    line-height: 1.5;
}

.sub {
    margin-top: 10px;
    font-size: 0.78rem;

    .regDate {
        margin-left: 10px;
        color: #6b6b6b;

    }
}
</style>