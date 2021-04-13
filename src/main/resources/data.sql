insert into Movie(name, description)
values('test', 'I am a super beast');


insert into Movie_session(movie_id, movie_session_date)
values((SELECT ID FROM Movie WHERE name = 'test'), current_date);

insert into Movie_session(movie_id, movie_session_date)
values((SELECT ID FROM Movie WHERE name = 'test'), current_date);