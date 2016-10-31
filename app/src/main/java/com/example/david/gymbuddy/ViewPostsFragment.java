package com.example.david.gymbuddy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPostsFragment extends Fragment
{
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;
    private PostAdapter mPostRVAdapter;
    private List<Post> mPostList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_recycler_view_view_posts, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_view_posts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_posts);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                mSwipeRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    public void setupRVAdapter()
    {
        mPostList = ((PostBoardActivity)getActivity()).getPostList();

        if(isAdded() && mPostList.size() != 0)
        {
            if(mPostRVAdapter == null)
            {
                mPostRVAdapter = new PostAdapter(mPostList);
                mRecyclerView.setAdapter(mPostRVAdapter);
            }
            else
            {
                mPostRVAdapter.updatePostList(mPostList);
                mPostRVAdapter.notifyDataSetChanged();
            }
        }
        else
        {
            mRecyclerView.setAdapter(new PostAdapter(null));
        }
    }

    public void updatePostList(List<Post> updatedPostList)
    {
        mPostList = updatedPostList;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    private class PostAdapter extends RecyclerView.Adapter<PostHolder>
    {
        private List<Post> mPosts;

        public PostAdapter(List<Post> postList)
        {
            mPosts = postList;
        }

        @Override
        public PostHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_post_item, parent, false);
            return new PostHolder(view, getActivity());
        }

        @Override
        public void onBindViewHolder(PostHolder holder, int position)
        {
            holder.bindPostData(mPosts.get(position));
        }

        @Override
        public int getItemCount()
        {
            if(mPosts == null)
                return 0;

            return mPosts.size();
        }

        public void updatePostList(List<Post> updatedPostList)
        {
            mPosts = updatedPostList;
        }
    }
}
