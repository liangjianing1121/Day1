package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.day1.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import bean.News2;

/**
 * Created by DELL on 2017/8/29.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<News2> list;

    public MyAdapter(Context context, List<News2> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder=null;
        if(view==null) {

            holder=new ViewHolder();
            view = View.inflate(context, R.layout.lv_item, null);
            holder.tv_title=view.findViewById(R.id.tv_title);
            holder.iv=view.findViewById(R.id.iv);
            view.setTag(holder);

        }
        else {

            holder= (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(list.get(i).getTitle());
        ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),holder.iv);

        return view;
    }

    public class  ViewHolder{

        TextView tv_title;
        ImageView iv;



    }
}
