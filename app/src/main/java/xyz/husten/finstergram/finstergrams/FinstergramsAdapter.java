package xyz.husten.finstergram.finstergrams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.List;
import xyz.husten.finstergram.R;
import xyz.husten.finstergram.model.Result;

public class FinstergramsAdapter extends RecyclerView.Adapter<FinstergramsAdapter.ResultViewHolder> {
  private List<Result> items;
  private final Picasso picasso;
  private final OnResultClickListener clickListener;

  public FinstergramsAdapter(List<Result> items, Picasso picasso, OnResultClickListener clickListener) {
    this.items = items;
    this.picasso = picasso;
    this.clickListener = clickListener;
  }

  @Override public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_result_view, parent, false);
    return new ResultViewHolder(view);
  }

  @Override public void onBindViewHolder(ResultViewHolder holder, int position) {
    final Result result = items.get(position);
    holder.likeCount.setText("❤️ " + result.likes.count);
    picasso.load(result.images.thumbnail.url).into(holder.imageView);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        clickListener.onClick(result);
      }
    });

  }

  @Override public int getItemCount() {
    return items.size();
  }

  public static class ResultViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_image) ImageView imageView;
    @BindView(R.id.tv_like_count) TextView likeCount;

    public ResultViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnResultClickListener {
    void onClick(Result result);
  }
}
