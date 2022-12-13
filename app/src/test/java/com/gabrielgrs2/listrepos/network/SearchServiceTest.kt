package com.gabrielgrs2.listrepos.network

import com.gabrielgrs2.listrepos.data.api.ISearchService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private val mockResponseSearch = """
            {
  "total_count": 997397,
  "incomplete_results": false,
  "items": [
    {
      "id": 3432266,
      "node_id": "MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
      "name": "kotlin",
      "full_name": "JetBrains/kotlin",
      "private": false,
      "owner": {
        "login": "JetBrains",
        "id": 878437,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==",
        "avatar_url": "https://avatars.githubusercontent.com/u/878437?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/JetBrains",
        "html_url": "https://github.com/JetBrains",
        "followers_url": "https://api.github.com/users/JetBrains/followers",
        "following_url": "https://api.github.com/users/JetBrains/following{/other_user}",
        "gists_url": "https://api.github.com/users/JetBrains/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/JetBrains/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/JetBrains/subscriptions",
        "organizations_url": "https://api.github.com/users/JetBrains/orgs",
        "repos_url": "https://api.github.com/users/JetBrains/repos",
        "events_url": "https://api.github.com/users/JetBrains/events{/privacy}",
        "received_events_url": "https://api.github.com/users/JetBrains/received_events",
        "type": "Organization",
        "site_admin": false
      },
      "html_url": "https://github.com/JetBrains/kotlin",
      "description": "The Kotlin Programming Language. ",
      "fork": false,
      "url": "https://api.github.com/repos/JetBrains/kotlin",
      "forks_url": "https://api.github.com/repos/JetBrains/kotlin/forks",
      "keys_url": "https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/JetBrains/kotlin/teams",
      "hooks_url": "https://api.github.com/repos/JetBrains/kotlin/hooks",
      "issue_events_url": "https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}",
      "events_url": "https://api.github.com/repos/JetBrains/kotlin/events",
      "assignees_url": "https://api.github.com/repos/JetBrains/kotlin/assignees{/user}",
      "branches_url": "https://api.github.com/repos/JetBrains/kotlin/branches{/branch}",
      "tags_url": "https://api.github.com/repos/JetBrains/kotlin/tags",
      "blobs_url": "https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/JetBrains/kotlin/languages",
      "stargazers_url": "https://api.github.com/repos/JetBrains/kotlin/stargazers",
      "contributors_url": "https://api.github.com/repos/JetBrains/kotlin/contributors",
      "subscribers_url": "https://api.github.com/repos/JetBrains/kotlin/subscribers",
      "subscription_url": "https://api.github.com/repos/JetBrains/kotlin/subscription",
      "commits_url": "https://api.github.com/repos/JetBrains/kotlin/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/JetBrains/kotlin/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/JetBrains/kotlin/contents/{+path}",
      "compare_url": "https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/JetBrains/kotlin/merges",
      "archive_url": "https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/JetBrains/kotlin/downloads",
      "issues_url": "https://api.github.com/repos/JetBrains/kotlin/issues{/number}",
      "pulls_url": "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/JetBrains/kotlin/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/JetBrains/kotlin/labels{/name}",
      "releases_url": "https://api.github.com/repos/JetBrains/kotlin/releases{/id}",
      "deployments_url": "https://api.github.com/repos/JetBrains/kotlin/deployments",
      "created_at": "2012-02-13T17:29:58Z",
      "updated_at": "2022-12-12T23:36:55Z",
      "pushed_at": "2022-12-12T23:52:24Z",
      "git_url": "git://github.com/JetBrains/kotlin.git",
      "ssh_url": "git@github.com:JetBrains/kotlin.git",
      "clone_url": "https://github.com/JetBrains/kotlin.git",
      "svn_url": "https://github.com/JetBrains/kotlin",
      "homepage": "https://kotlinlang.org",
      "size": 1625692,
      "stargazers_count": 43318,
      "watchers_count": 43318,
      "language": "Kotlin",
      "has_issues": false,
      "has_projects": false,
      "has_downloads": true,
      "has_wiki": false,
      "has_pages": false,
      "has_discussions": false,
      "forks_count": 5366,
      "mirror_url": null,
      "archived": false,
      "disabled": false,
      "open_issues_count": 161,
      "license": null,
      "allow_forking": true,
      "is_template": false,
      "web_commit_signoff_required": false,
      "topics": [
        "compiler",
        "gradle-plugin",
        "intellij-plugin",
        "kotlin",
        "kotlin-library",
        "maven-plugin",
        "programming-language"
      ],
      "visibility": "public",
      "forks": 5366,
      "open_issues": 161,
      "watchers": 43318,
      "default_branch": "master",
      "score": 1.0
    },
    {
      "id": 5152285,
      "node_id": "MDEwOlJlcG9zaXRvcnk1MTUyMjg1",
      "name": "okhttp",
      "full_name": "square/okhttp",
      "private": false,
      "owner": {
        "login": "square",
        "id": 82592,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjgyNTky",
        "avatar_url": "https://avatars.githubusercontent.com/u/82592?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/square",
        "html_url": "https://github.com/square",
        "followers_url": "https://api.github.com/users/square/followers",
        "following_url": "https://api.github.com/users/square/following{/other_user}",
        "gists_url": "https://api.github.com/users/square/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/square/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/square/subscriptions",
        "organizations_url": "https://api.github.com/users/square/orgs",
        "repos_url": "https://api.github.com/users/square/repos",
        "events_url": "https://api.github.com/users/square/events{/privacy}",
        "received_events_url": "https://api.github.com/users/square/received_events",
        "type": "Organization",
        "site_admin": false
      },
      "html_url": "https://github.com/square/okhttp",
      "description": "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
      "fork": false,
      "url": "https://api.github.com/repos/square/okhttp",
      "forks_url": "https://api.github.com/repos/square/okhttp/forks",
      "keys_url": "https://api.github.com/repos/square/okhttp/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/square/okhttp/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/square/okhttp/teams",
      "hooks_url": "https://api.github.com/repos/square/okhttp/hooks",
      "issue_events_url": "https://api.github.com/repos/square/okhttp/issues/events{/number}",
      "events_url": "https://api.github.com/repos/square/okhttp/events",
      "assignees_url": "https://api.github.com/repos/square/okhttp/assignees{/user}",
      "branches_url": "https://api.github.com/repos/square/okhttp/branches{/branch}",
      "tags_url": "https://api.github.com/repos/square/okhttp/tags",
      "blobs_url": "https://api.github.com/repos/square/okhttp/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/square/okhttp/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/square/okhttp/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/square/okhttp/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/square/okhttp/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/square/okhttp/languages",
      "stargazers_url": "https://api.github.com/repos/square/okhttp/stargazers",
      "contributors_url": "https://api.github.com/repos/square/okhttp/contributors",
      "subscribers_url": "https://api.github.com/repos/square/okhttp/subscribers",
      "subscription_url": "https://api.github.com/repos/square/okhttp/subscription",
      "commits_url": "https://api.github.com/repos/square/okhttp/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/square/okhttp/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/square/okhttp/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/square/okhttp/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/square/okhttp/contents/{+path}",
      "compare_url": "https://api.github.com/repos/square/okhttp/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/square/okhttp/merges",
      "archive_url": "https://api.github.com/repos/square/okhttp/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/square/okhttp/downloads",
      "issues_url": "https://api.github.com/repos/square/okhttp/issues{/number}",
      "pulls_url": "https://api.github.com/repos/square/okhttp/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/square/okhttp/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/square/okhttp/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/square/okhttp/labels{/name}",
      "releases_url": "https://api.github.com/repos/square/okhttp/releases{/id}",
      "deployments_url": "https://api.github.com/repos/square/okhttp/deployments",
      "created_at": "2012-07-23T13:42:55Z",
      "updated_at": "2022-12-12T18:44:17Z",
      "pushed_at": "2022-12-12T14:32:32Z",
      "git_url": "git://github.com/square/okhttp.git",
      "ssh_url": "git@github.com:square/okhttp.git",
      "clone_url": "https://github.com/square/okhttp.git",
      "svn_url": "https://github.com/square/okhttp",
      "homepage": "https://square.github.io/okhttp/",
      "size": 47776,
      "stargazers_count": 43268,
      "watchers_count": 43268,
      "language": "Kotlin",
      "has_issues": true,
      "has_projects": false,
      "has_downloads": true,
      "has_wiki": false,
      "has_pages": true,
      "has_discussions": false,
      "forks_count": 8981,
      "mirror_url": null,
      "archived": false,
      "disabled": false,
      "open_issues_count": 157,
      "license": {
        "key": "apache-2.0",
        "name": "Apache License 2.0",
        "spdx_id": "Apache-2.0",
        "url": "https://api.github.com/licenses/apache-2.0",
        "node_id": "MDc6TGljZW5zZTI="
      },
      "allow_forking": true,
      "is_template": false,
      "web_commit_signoff_required": false,
      "topics": [
        "android",
        "graalvm",
        "java",
        "kotlin"
      ],
      "visibility": "public",
      "forks": 8981,
      "open_issues": 157,
      "watchers": 43268,
      "default_branch": "master",
      "score": 1.0
    }
  ]
}
            }
    """.trimIndent()

    private val mockResponseSearchGithub = "[$mockResponseSearch]"


    /**
     * Helper method to get a CharacterService instance
     */
    private fun getSearchService(): ISearchService {
        val baseUrl = mockWebServer.url("https://api.github.com")
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ISearchService::class.java)
    }

    @Test
    fun getSearchList_canBeParsed() = runBlocking {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseSearchGithub))
        mockWebServer.start()

        val service = getSearchService()

        val search = service.getSearchRepositories(
            query = "language:kotlin",
            sort = "stars",
            page = 1
        )
        assertFalse(search.repositories.isEmpty())
        assertTrue(search.repositories[0].id == 3432266)
        assertTrue(search.repositories[0].name == "kotlin")
        assertTrue(search.repositories[0].forksCount == 5366)
        assertTrue(search.repositories[0].starGazersCount == 43318)
        assertTrue(search.repositories[0].owner.login == "JetBrains")
        assertTrue(search.repositories[0].owner.avatarUrl == "https://avatars.githubusercontent.com/u/878437?v=4")

        mockWebServer.shutdown()
    }
}