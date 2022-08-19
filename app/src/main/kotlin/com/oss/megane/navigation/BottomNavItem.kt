package com.oss.megane.navigation

import androidx.annotation.StringRes
import com.oss.megane.R

sealed class BottomNavItem(@StringRes val titleResId: Int, val icon: Int, val route: String) {
    object Home :
        BottomNavItem(R.string.title_home, R.drawable.ic_outline_home_24, "Home")

    object Account :
        BottomNavItem(R.string.title_account, R.drawable.ic_outline_account_circle_24, "Account")
}
