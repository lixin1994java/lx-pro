#!/usr/bin/env bash

set -euo pipefail

usage() {
  cat <<'EOF'
用法:
  ./scripts/cherry-pick-release.sh <commit> [release-branch] [base-branch]

参数:
  commit         必填。要发布的 commit hash（或可解析的 commit-ish）
  release-branch 可选。默认: cursor/release-<commit前7位>-7dc4
  base-branch    可选。默认: master

示例:
  ./scripts/cherry-pick-release.sh 39452dc
  ./scripts/cherry-pick-release.sh 39452dc cursor/release-apple-7dc4 master
EOF
}

if [[ "${1:-}" == "-h" || "${1:-}" == "--help" ]]; then
  usage
  exit 0
fi

if [[ $# -lt 1 ]]; then
  usage
  exit 1
fi

commit_ref="$1"
base_branch="${3:-master}"
short_ref="${commit_ref:0:7}"
release_branch="${2:-cursor/release-${short_ref}-7dc4}"

echo "==> 检查工作区状态"
if [[ -n "$(git status --porcelain)" ]]; then
  echo "错误: 工作区不干净，请先提交或暂存当前修改。"
  exit 1
fi

echo "==> 校验 commit 是否存在: ${commit_ref}"
if ! git rev-parse --verify "${commit_ref}^{commit}" >/dev/null 2>&1; then
  echo "错误: 找不到 commit ${commit_ref}"
  exit 1
fi

echo "==> 拉取远端分支: origin/${base_branch}"
git fetch origin "${base_branch}"

echo "==> 创建发布分支: ${release_branch} (基于 origin/${base_branch})"
git checkout -b "${release_branch}" "origin/${base_branch}"

echo "==> cherry-pick: ${commit_ref}"
if ! git cherry-pick "${commit_ref}"; then
  if git status | rg -q "nothing to commit, working tree clean"; then
    echo "检测到空 cherry-pick，自动跳过。"
    git cherry-pick --skip
  else
    echo "cherry-pick 发生冲突，请手动解决后执行:"
    echo "  git add <files>"
    echo "  git cherry-pick --continue"
    echo "或放弃本次操作:"
    echo "  git cherry-pick --abort"
    exit 1
  fi
fi

echo "==> 推送分支: ${release_branch}"
git push -u origin "${release_branch}"

echo ""
echo "完成 ✅"
echo "当前分支: ${release_branch}"
echo "下一步建议:"
echo "  1) 创建 PR 到 ${base_branch}"
echo "  2) 合并后删除发布分支"
