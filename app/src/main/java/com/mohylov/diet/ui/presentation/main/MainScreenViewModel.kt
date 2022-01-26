package com.mohylov.diet.ui.presentation.main
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) : BaseViewModel<MainScreenViewState, MainScreenViewActions>() {


    init {
        initViewState()
        productsInteractor.getFoods().onEach {
            updateState(
                getViewState().copy(
                    foodList = it
                )
            )
        }.launchIn(viewModelScope)
    }


    private fun initViewState() {
        stateData.value = MainScreenViewState(
            isLoading = true,
            foodList = emptyList()
        )
    }

    private fun updateState(mainScreenViewState: MainScreenViewState) {
        stateData.value = mainScreenViewState
    }

    private fun getViewState() = stateData.value as MainScreenViewState


}

data class MainScreenViewState(
    val isLoading: Boolean,
    val foodList: List<ProductItem>
)

sealed class MainScreenViewActions {

}