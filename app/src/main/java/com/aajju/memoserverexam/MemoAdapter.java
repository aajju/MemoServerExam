package com.aajju.memoserverexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aajju on 2017-03-03.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    public interface  OnMemoItemClickListener{
        void onMemoItemClicked(int position, Memo memo);

        void onMemoItemLongClicked(int position, Memo memo);
    }

    private List<Memo> mData = new ArrayList<>();
    private OnMemoItemClickListener mListener;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public MemoAdapter(OnMemoItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
        Memo item = mData.get(position);
        holder.mIdTextView.setText(item.getId()+"");
        holder.mTitleTextView.setText(item.getTitle());
        holder.mContentsTextView.setText(item.getContents());
        holder.mTimeTextView.setText(mFormat.format(new Date(item.getTime())));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void swapData(List<Memo> memos){
        mData = memos;
        notifyDataSetChanged();
    }

    public void addData(Memo memo){
        mData.add(memo);
        notifyItemInserted(mData.size()-1);
    }

    public void updateData(int id, Memo memo){
        for(int i = 0 ; i < mData.size() ; i++){
            if(mData.get(i).getId() == id) {
                mData.add(i, memo);
                mData.remove(i+1);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void deleteData(int id){
        for(int i=0; i<mData.size(); i++){
            if(mData.get(i).getId() == id){
                mData.remove(i);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public class MemoViewHolder extends RecyclerView.ViewHolder {

        TextView mIdTextView, mTitleTextView, mContentsTextView, mTimeTextView;

        public MemoViewHolder(View itemView, final OnMemoItemClickListener listener) {
            super(itemView);

            if(listener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onMemoItemClicked(getAdapterPosition(), mData.get(getAdapterPosition()));

                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        listener.onMemoItemLongClicked(getAdapterPosition(), mData.get(getAdapterPosition()));

                        return true; // true면 롱클릭 햇다
                    }
                });
            }

            mIdTextView = (TextView) itemView.findViewById(R.id.memo_id_tv);
            mTitleTextView = (TextView) itemView.findViewById(R.id.memo_title_tv);
            mContentsTextView = (TextView) itemView.findViewById(R.id.memo_contents_tv);
            mTimeTextView = (TextView) itemView.findViewById(R.id.memo_time_tv);
        }
    }
}
