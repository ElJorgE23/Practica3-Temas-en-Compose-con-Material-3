package com.example.reply.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import androidx.compose.material3.CardDefaults // Asegúrate de tener esta importación

@OptIn(ExperimentalMaterial3Api::class) // Asegúrate de usar este opt-in si estás usando componentes experimentales
@Composable
fun ReplyEmailListItem(
    email: Email,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clickable { navigateToDetail(email.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ReplyProfileImage(
                    drawableResource = email.sender.avatar,
                    description = email.sender.fullName,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = email.sender.firstName,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = email.createdAt,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = { /* Implementar acción para marcar como favorito */ },
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = stringResource(id = R.string.description_favorite),
                    )
                }
            }

            Text(
                text = email.subject,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )

            Text(
                text = email.body,
                maxLines = 2,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
