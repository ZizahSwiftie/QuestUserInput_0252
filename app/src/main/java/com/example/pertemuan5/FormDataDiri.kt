package com.example.pertemuan5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun FormDataDiri(modifier: Modifier = Modifier) {
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    val gender: List<String> = listOf(
        stringResource(R.string.jk_laki),
        stringResource(R.string.jk_perempuan)
    )

    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_atas)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textNama,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(dimensionResource(R.dimen.lebar_field)),
            label = { Text(text = stringResource(R.string.label_nama)) },
            onValueChange = { textNama = it }
        )

        Row {
            gender.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = textJK == item,
                        onClick = { textJK = item }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = textJK == item,
                        onClick = { textJK = item }
                    )
                    Text(item)
                }
            }
        }

        OutlinedTextField(
            value = textAlamat,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(dimensionResource(R.dimen.lebar_field)),
            label = { Text(text = stringResource(R.string.label_alamat)) },
            onValueChange = { textAlamat = it }
        )

        HorizontalDivider(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            thickness = dimensionResource(R.dimen.divider_tipis),
            color = Color.DarkGray
        )

        Button(
            modifier = Modifier.fillMaxWidth(1f),
            enabled = textAlamat.isNotEmpty(),
            onClick = {
                nama = textNama
                jenis = textJK
                alamat = textAlamat
            }
        ) {
            Text(stringResource(R.string.label_submit))
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.card_elevation)
            ),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            modifier = Modifier
                .height(dimensionResource(R.dimen.card_tinggi))
                .width(dimensionResource(R.dimen.card_lebar))
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_kecil),
                    vertical = dimensionResource(R.dimen.padding_teks_vertikal)
                )
            ) {
                Text(text = stringResource(R.string.label_hasil_nama) + nama, color = Color.White)
                Text(text = stringResource(R.string.label_hasil_gender) + jenis, color = Color.White)
                Text(text = stringResource(R.string.label_hasil_alamat) + alamat, color = Color.White)
            }
        }

        if (nama.isNotEmpty()) {
            Card(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                Column(Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                    Text(stringResource(R.string.title_hasil), style = MaterialTheme.typography.titleMedium)
                    Text("${stringResource(R.string.label_nama)}: $nama")
                    Text("Jenis Kelamin: $jenis")
                    Text("${stringResource(R.string.label_alamat)}: $alamat")
                }
            }
        }
    }
}