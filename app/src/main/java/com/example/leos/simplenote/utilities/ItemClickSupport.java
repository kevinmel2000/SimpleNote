package com.example.leos.simplenote.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.leos.simplenote.R;


public class ItemClickSupport {
    private RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;

    private ItemClickSupport(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListnener);
    }

    private RecyclerView.OnChildAttachStateChangeListener mAttachListnener = new
            RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    if (mOnItemClickListener != null){
                        view.setOnClickListener(mOnClickListener);
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null){
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    public static ItemClickSupport addTo(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null){
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support != null){
            support.detach(view);
        }
        return support;
    }
    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
        return this;
    }
    private void detach(RecyclerView view){
        view.removeOnChildAttachStateChangeListener(mAttachListnener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener{
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }
}
