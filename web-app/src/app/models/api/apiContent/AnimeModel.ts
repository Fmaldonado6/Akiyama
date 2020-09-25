import { EpisodeModel } from './EpisodeModel'

export class AnimeModel {
    id: String
    title: String
    type: String
    banner: String
    image: String
    poster: String

    synopsis: String
    status: String
    genres: String[]
    episodes: EpisodeModel[]
}