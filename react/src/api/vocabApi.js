import axios from "axios";

/**
 * SpringBootで作成したAPIと連携するためのモジュール
 */
const vocabApi = axios.create({
  baseURL: "http://localhost:8080/api/vocabs",
  headers: { "Content-Type": "application/json" }
});

// 全単語データの取得
export async function getVocabs () {
  const res = await vocabApi.get("");
  return res.data;
}

// IDに基づき、単語データを1件取得する
export async function getVocabById (id) {
  try {
    const res = await vocabApi.get(`/${id}`); 
    if (res.status !== 200) {   
      throw new Error();
    }
    return res.data;
  } catch(error) {
    console.log(error);
    throw new Error("単語情報の取得に失敗しました");
  }
}

// 単語の種類に基づく単語リストの取得
export async function getVocabsByType (typeId) {
  const res = await vocabApi.get(`/type/${typeId}`);
  return res.data;
}

// 単語の種類リストの取得
export async function getVocabTypes () {
  const res = await vocabApi.get("/types");
  if (res.data.length === 0) {
    throw new Error("単語の種類が存在しません");
  }
  return res.data;
}

// 単語データの追加
export async function addVocab (vocab) {
  const res = await vocabApi.post("", vocab);
  if (res.status !== 200) {
    throw new Error("単語の登録に失敗しました");
  }
  return res.data;
}

// 単語データの編集
export async function updateVocab (vocab) {
  const res = await vocabApi.put("", vocab);
  if (res.status !== 200) {
    throw new Error("単語情報の更新に失敗しました");
  }
  return res.data;
}

// IDに基づき、単語データを1件削除する
export async function deleteVocab (id) {
  try {
    const res = await vocabApi.delete(`/${id}`);
    return res.data;
  } catch (error) {
    console.log(error);
    throw new Error("単語の削除に失敗しました");
  }
}
