package levkaantonov.com.study.searchimage.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import levkaantonov.com.study.searchimage.databinding.PhotoLoadStateFooterBinding

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            PhotoLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class LoadStateViewHolder(private val binding: PhotoLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}