package com.coldblue.mandalart.state

import com.coldblue.mandalart.model.MandaUI
import com.coldblue.model.UpdateNote

sealed interface MandaUpdateDialogState {
    data class Error(val msg: String) : MandaUpdateDialogState
    data object Down : MandaUpdateDialogState
    data class Up(val mandaBottomSheetContentState: UpdateNote) :
        MandaUpdateDialogState
}