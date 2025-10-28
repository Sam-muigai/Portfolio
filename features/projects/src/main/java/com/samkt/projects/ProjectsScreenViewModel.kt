package com.samkt.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Project
import com.samkt.domain.repositories.ProjectRepository
import com.samkt.domain.utils.Constants.USER_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProjectsScreenViewModel(
  private val projectRepository: ProjectRepository,
) : ViewModel() {

  private val _projectScreenUiState = MutableStateFlow<ProjectsScreenUiState>(ProjectsScreenUiState.Loading)
  val projectScreenUiState = _projectScreenUiState.asStateFlow()

  init {
    getProjects()
  }

  private fun getProjects() {
    viewModelScope.launch {
      when (val result = projectRepository.getAllProjects(USER_ID)) {
        is Result.Error -> {
          _projectScreenUiState.update { ProjectsScreenUiState.Error(result.message) }
        }

        is Result.Success -> {
          _projectScreenUiState.update { ProjectsScreenUiState.Success(result.data) }
        }
      }
    }
  }
}

sealed interface ProjectsScreenUiState {
  data object Loading : ProjectsScreenUiState
  data class Success(val projects: List<Project>) : ProjectsScreenUiState
  data class Error(val message: String) : ProjectsScreenUiState
}

fun String.formatLink(): String {
  return if (!this.startsWith("http://") && !this.startsWith("https://")) {
    "https://$this"
  } else {
    this
  }
}
