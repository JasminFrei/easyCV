package com.zuehlke.training.easycv.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    /**
     * Checks if the chosen Activity in the intent matches with the destination of the given
     * direction
     */
    private fun isNavigationLegal(intent: Intent?, direction: NavDirections): Boolean {
        val className = intent?.component?.className
        val actionId = direction.actionId
        val navAction = findNavController().currentBackStackEntry?.destination?.getAction(actionId)
        val destinationId = navAction?.destinationId
        if (destinationId != null) {
            val destination = findNavController().graph.findNode(destinationId)
            if (destination is ActivityNavigator.Destination) {
                if (destination.component?.className == className) {
                    return true
                }
            }
        }
        return false
    }

    fun navigateToActivity(intent: Intent?, direction: NavDirections) {
        if (isNavigationLegal(intent, direction)) {
            startActivity(intent)
        } else {
            throw IllegalArgumentException()
        }
    }

    fun navigateToActivityForResult(intent: Intent?, direction: NavDirections, requestCode: Int) {
        if (isNavigationLegal(intent, direction)) {
            startActivityForResult(intent, requestCode)
        } else {
            throw IllegalArgumentException()
        }
    }
}