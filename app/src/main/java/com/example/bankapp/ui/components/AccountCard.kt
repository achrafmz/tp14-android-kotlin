package com.example.bankapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankapp.data.Account
import com.example.bankapp.viewmodel.AccountViewModel

@Composable
fun AccountCard(
    account: Account,
    onModify: () -> Unit,
    onDelete: () -> Unit,
    viewModel: AccountViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = account.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = account.formattedBalance(),
                color = Color.Green,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(account.typeColor()),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = account.typeLabel(),
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onModify,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Modifier", color = Color.White, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Supprimer", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = account.formattedDate(),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}