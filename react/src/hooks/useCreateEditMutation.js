import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { deleteVocab, updateVocab } from "../api/vocabApi";

/**
 * 編集用ミューテーションの生成フック
 * 引数: 単語ID
 */
export default function useCreateEditMutation(vocabId) {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const updateMutation = useMutation({
    mutationFn: (vocab) => updateVocab(vocab),
    onSuccess: async () => {
      queryClient.invalidateQueries({ queryKey: ["vocab", vocabId] });
      queryClient.invalidateQueries({ queryKey: ["vocabs", 0] });
    }
  });

  const deleteMutation = useMutation({
    mutationFn: () => deleteVocab(vocabId),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ["vocabs"] });
      navigate("/", { replace: true });
    }
  });

  return { updateMutation, deleteMutation };
}
