package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.evenclose.versalistpro.presentation.ui.theme.onLight

@Composable
fun ListCategoryItem(
    categoryType: String
) {

    Box() {
        Column {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "category icon",
                tint = onLight
            )
            Text(
                text = "text",
                color = onLight,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


@Composable
@Preview
fun ListCategoryItemPreview() {
    ListCategoryItem(
        categoryType = "shopping"
    )
}