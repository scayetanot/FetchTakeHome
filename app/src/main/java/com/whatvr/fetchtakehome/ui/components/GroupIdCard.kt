package com.whatvr.fetchtakehome.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whatvr.fetchtakehome.repositories.model.HiringItem

@Composable
fun GroupIdCard(
    listId: Int,
    listOfHiring: List<HiringItem>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row {
            Text("List ID:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
            Spacer(Modifier.weight(1f).fillMaxWidth())
            Text(text = listId.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
        }
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
        listOfHiring.sortedBy { it.nameId }.forEach {
            Row {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 2.dp)
                )
            }
        }

    }
}